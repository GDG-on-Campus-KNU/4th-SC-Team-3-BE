package pipy.project.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pipy.project.persistence.ProjectJpaRepository;

@Component
@RequiredArgsConstructor
public class ProjectReader {

    private final ProjectJpaRepository repository;

    public Project readById(final Long projectId) {
        return repository.findById(projectId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 프로젝트입니다."));
    }
}
