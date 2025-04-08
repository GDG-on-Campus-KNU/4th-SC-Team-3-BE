package pipy.edge.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pipy.node.domain.Node;
import pipy.node.domain.NodeReader;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EdgeCommandService {

    private final NodeReader nodeReader;

    @Transactional
    public void createEdge(final Long sourceId, final Long targetId) {
        final Node source = nodeReader.readById(sourceId);
        final Node target = nodeReader.readById(targetId);
        source.connectTo(target);
    }
}
