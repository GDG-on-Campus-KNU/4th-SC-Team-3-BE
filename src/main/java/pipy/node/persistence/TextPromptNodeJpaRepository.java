package pipy.node.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pipy.node.domain.TextPromptNode;

public interface TextPromptNodeJpaRepository extends JpaRepository<TextPromptNode, Long> {
}
