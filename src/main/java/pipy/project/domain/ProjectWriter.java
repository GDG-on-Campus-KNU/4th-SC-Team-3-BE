package pipy.project.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pipy.project.persistence.ProjectJpaRepository;

@Component
@RequiredArgsConstructor
public class ProjectWriter {

    private final ProjectJpaRepository repository;

    public Project write(final Project project) {
        return repository.save(project);
    }
}
