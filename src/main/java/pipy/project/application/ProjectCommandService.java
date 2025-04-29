package pipy.project.application;

import com.github.fge.jsonpatch.JsonPatch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pipy.member.domain.Member;
import pipy.member.domain.MemberReader;
import pipy.project.domain.Project;
import pipy.project.domain.ProjectReader;
import pipy.project.domain.ProjectWriter;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectCommandService {

    private final MemberReader memberReader;
    private final ProjectReader projectReader;
    private final ProjectWriter projectWriter;

    @Transactional
    public Project createProject(final String email, final String canvas) {
        final Member owner = memberReader.readByEmail(email);
        return projectWriter.write(owner, canvas);
    }

    @Transactional
    public void updateCanvas(final Long projectId, final JsonPatch canvas) {
        final Project project = projectReader.readById(projectId);
        project.updateCanvas(canvas);
    }
}
