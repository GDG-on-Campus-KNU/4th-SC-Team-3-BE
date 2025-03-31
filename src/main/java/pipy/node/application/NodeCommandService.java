package pipy.node.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pipy.node.presentation.dto.request.NodeRequest;
import pipy.node.domain.Node;
import pipy.node.domain.NodeWriter;
import pipy.node.domain.command.NodeCommand;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NodeCommandService {

    private final List<NodeWriter> writers;

    @Transactional
    public Node createNode(final NodeRequest request) {
        final NodeCommand command = NodeCommandMapper.mapToCommand(request);
        return writers.stream()
            .filter(writer -> writer.supports(command))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 노드 유형입니다."))
            .write(command);
    }
}
