package pipy.node.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pipy.node.domain.*;
import pipy.node.domain.command.NodeCommand;
import pipy.node.presentation.dto.request.NodeRequest;
import pipy.node.presentation.dto.response.NodeAnalyzeResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NodeCommandService {

    private final NodeReader nodeReader;
    private final NodeAnalyzer nodeAnalyzer;
    private final List<NodeWriter> nodeWriters;

    @Transactional
    public Node createNode(final NodeRequest request) {
        final NodeCommand command = NodeCommandMapper.mapToCommand(request);
        return nodeWriters.stream()
            .filter(writer -> writer.supports(command))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 노드 유형입니다."))
            .write(command);
    }

    public List<NodeAnalyzeResponse> analyzeNode(final Long nodeId) {
        final Node node = nodeReader.readById(nodeId);
        if (!(node instanceof Analyzable analyzable)) {
            throw new IllegalArgumentException("분석할 수 없는 노드 유형입니다.");
        }
        return nodeAnalyzer.analyze(analyzable).stream()
            .map(NodeAnalyzeResponse::from)
            .toList();
    }
}
