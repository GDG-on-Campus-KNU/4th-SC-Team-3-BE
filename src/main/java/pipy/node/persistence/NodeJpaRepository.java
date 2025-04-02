package pipy.node.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pipy.node.domain.Node;

public interface NodeJpaRepository extends JpaRepository<Node, Long> {
}
