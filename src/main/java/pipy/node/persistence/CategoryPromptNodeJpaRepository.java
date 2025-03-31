package pipy.node.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pipy.node.domain.CategoryPromptNode;

public interface CategoryPromptNodeJpaRepository extends JpaRepository<CategoryPromptNode, Long> {
}
