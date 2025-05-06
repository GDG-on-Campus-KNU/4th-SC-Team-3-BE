package pipy.project.application;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pipy.member.domain.Member;
import pipy.project.domain.Project;
import pipy.project.domain.ProjectDeleter;
import pipy.project.domain.ProjectReader;
import pipy.project.domain.ProjectWriter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectCommandService {

    private final ProjectReader projectReader;
    private final ProjectWriter projectWriter;
    private final ProjectDeleter projectDeleter;

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
    public void updateCanvas(final Member member, final Long projectId, final JsonPatch canvas) {
        final Project project = projectReader.readById(projectId);
        if (!project.isOwner(member)) {
            throw new IllegalArgumentException("You are not the owner of this project");
        }
        project.updateCanvas(canvas);
    }

    @Transactional
    public void updateName(final Member member, final Long projectId, final String name) {
        final Project project = projectReader.readById(projectId);
        if (!project.isOwner(member)) {
            throw new IllegalArgumentException("You are not the owner of this project");
        }
        project.updateName(name);
    }

    public void updateThumbnail(
        final Member member,
        final Long projectId,
        final MultipartFile thumbnail
    ) {
        final String ext = thumbnail.getContentType();
        if (ext == null || !ext.startsWith("image/")) {
            throw new IllegalArgumentException("Invalid image type");
        }

        final Project project = projectReader.readById(projectId);
        if (!project.isOwner(member)) {
            throw new IllegalArgumentException("You are not the owner of this project");
        }

        try {
            projectThumbnailSaver.save(project, thumbnail.getBytes());
        } catch (final IOException exception) {
            throw new IllegalArgumentException("Failed to read image", exception);
        }
    }

    @Transactional
    public void deleteProject(final Member member, final Long projectId) {
        final Project project = projectReader.readById(projectId);
        if (!project.isOwner(member)) {
            throw new IllegalArgumentException("You are not the owner of this project");
        }
        projectDeleter.deleteById(projectId);
    }
}
