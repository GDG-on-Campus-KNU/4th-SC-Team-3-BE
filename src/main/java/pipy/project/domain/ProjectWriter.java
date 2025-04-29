package pipy.project.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pipy.member.domain.Member;
import pipy.project.persistence.ProjectJpaRepository;

@Component
@RequiredArgsConstructor
public class ProjectWriter {

    private final ProjectJpaRepository repository;

    public Project write(final Member owner, final String name) {
        final Project project = Project.builder()
            .owner(owner)
            .name(name)
            .build();
        return repository.save(project);
    }
}
