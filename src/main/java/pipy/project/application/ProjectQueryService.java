package pipy.project.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pipy.member.domain.Member;
import pipy.project.domain.Project;
import pipy.project.domain.ProjectReader;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectQueryService {

    private final ProjectReader projectReader;

    public Project getProjectById(final Long projectId) {
        return projectReader.readById(projectId);
    }

    public List<Project> getProjectsByOwner(final Member owner) {
        return projectReader.readByOwner(owner);
    }
}
