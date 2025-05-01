package pipy.project.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pipy.project.domain.Project;

import java.util.List;

public interface ProjectJpaRepository extends JpaRepository<Project, Long> {

    List<Project> findByOwnerId(Long ownerId);
}
