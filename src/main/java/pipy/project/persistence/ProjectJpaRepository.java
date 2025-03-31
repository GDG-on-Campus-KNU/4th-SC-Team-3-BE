package pipy.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pipy.project.domain.Project;

public interface ProjectJpaRepository extends JpaRepository<Project, Long> {
}
