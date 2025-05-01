package pipy.node.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pipy.node.domain.NodeAnalyzeResult;
import pipy.node.domain.NodeAnalyzer;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DummyNodeAnalyzer implements NodeAnalyzer {

    @Override
    public List<NodeAnalyzeResult> analyze(final String content) {
        return List.of(
            new NodeAnalyzeResult("카테고리 키1", List.of("카테고리 값1, 카테고리 값2")),
            new NodeAnalyzeResult("카테고리 키2", List.of("카테고리 값1, 카테고리 값2"))
        );
    }
}
