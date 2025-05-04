package pipy.project.application;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pipy.member.domain.Member;
import pipy.project.domain.Project;
import pipy.project.domain.ProjectReader;
import pipy.project.domain.ProjectWriter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectCommandService {

    private final ProjectReader projectReader;
    private final ProjectWriter projectWriter;

    private final ProjectThumbnailSaver projectThumbnailSaver;

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

    public void updateThumbnail(
        final Long projectId,
        final MultipartFile thumbnail
    ) {
        final String ext = thumbnail.getContentType();
        if (ext == null || !ext.startsWith("image/")) {
            throw new IllegalArgumentException("Invalid image type");
        }

        final Project project = projectReader.readById(projectId);

        try {
            projectThumbnailSaver.save(project, thumbnail.getBytes());
        } catch (final IOException exception) {
            throw new IllegalArgumentException("Failed to read image", exception);
        }
    }
}
