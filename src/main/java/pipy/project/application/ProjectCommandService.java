package pipy.project.application;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pipy.member.domain.Member;
import pipy.node.application.ImageSaveCommand;
import pipy.node.application.ImageSaver;
import pipy.project.domain.Project;
import pipy.project.domain.ProjectReader;
import pipy.project.domain.ProjectWriter;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectCommandService {

    private final ProjectReader projectReader;
    private final ProjectWriter projectWriter;

    private final ImageSaver imageSaver;

    @Transactional
    public Project createProject(final CreateProjectCommand command) {
        final Member owner = command.owner();
        final String name = command.name();
        final String canvas = command.canvas();
        final Project project = Project.builder()
            .owner(owner)
            .name(name)
            .canvas(canvas)
            .build();
        return projectWriter.write(project);
    }

    @Transactional
    public void updateCanvas(final Long projectId, final JsonPatch canvas) {
        final Project project = projectReader.readById(projectId);
        project.updateCanvas(canvas);
    }

    @Transactional
    public void updateThumbnail(final Long projectId, final MultipartFile thumbnail) {
        final Project project = projectReader.readById(projectId);

        final String ext = thumbnail.getContentType();
        if (ext == null || !ext.startsWith("image/")) {
            throw new IllegalArgumentException("Invalid image type");
        }

        final Long memberId = project.getOwner().getId();
        final String uuid = UUID.randomUUID().toString();
        final String filename = String.format("%d/projects/%d/%s", memberId, projectId, uuid);

        try {
            final byte[] thumbnailBytes = thumbnail.getInputStream().readAllBytes();
            final ImageSaveCommand command = new ImageSaveCommand(
                thumbnail.getContentType(),
                filename,
                thumbnailBytes
            );
            final String thumbnailUrl = imageSaver.save(command);
            project.updateThumbnail(thumbnailUrl);
        } catch (final IOException exception) {
            throw new RuntimeException("Failed to upload thumbnail", exception);
        }
    }
}
