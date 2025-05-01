package pipy.node.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pipy.node.domain.*;
import pipy.node.presentation.dto.response.NodeAnalyzeResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NodeCommandService {

    private final NodeAnalyzer nodeAnalyzer;

    public List<NodeAnalyzeResponse> analyzeNode(final String content) {
        return nodeAnalyzer.analyze(content).stream()
            .map(NodeAnalyzeResponse::from)
            .toList();
    }
}
