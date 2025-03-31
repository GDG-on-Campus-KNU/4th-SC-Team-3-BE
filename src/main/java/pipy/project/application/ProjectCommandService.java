package pipy.project.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pipy.member.domain.Member;
import pipy.member.domain.MemberReader;
import pipy.project.domain.Project;
import pipy.project.domain.ProjectWriter;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectCommandService {

    private final MemberReader memberReader;
    private final ProjectWriter projectWriter;

    @Transactional
    public Project createProject(final String email) {
        final Member owner = memberReader.readByEmail(email);
        return projectWriter.write(owner);
    }
}
