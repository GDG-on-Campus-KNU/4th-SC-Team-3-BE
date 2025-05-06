package pipy.project.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pipy.project.persistence.ProjectJpaRepository;

@Component
@RequiredArgsConstructor
public class ProjectDeleter {

    private final ProjectJpaRepository repository;

    @Transactional
    public void deleteById(final Long projectId) {
        repository.deleteById(projectId);
    }
}
