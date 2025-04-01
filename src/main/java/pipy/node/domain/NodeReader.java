package pipy.node.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pipy.node.persistence.NodeJpaRepository;

@Component
@RequiredArgsConstructor
public class NodeReader {

    private final NodeJpaRepository repository;

    public Node readById(final Long nodeId) {
        return repository.findById(nodeId)
            .orElseThrow(() -> new IllegalArgumentException("해당 노드가 존재하지 않습니다."));
    }
}
