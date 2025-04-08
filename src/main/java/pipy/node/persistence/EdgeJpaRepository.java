package pipy.node.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pipy.edge.domain.Edge;

public interface EdgeJpaRepository extends JpaRepository<Edge, Long> {
}
