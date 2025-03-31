package pipy.node.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pipy.node.domain.GroupNode;

public interface GroupNodeJpaRepository extends JpaRepository<GroupNode, Long> {
}
