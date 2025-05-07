package pipy.node.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pipy.global.HttpResponseException;
import pipy.global.PipyException;
import pipy.node.application.exception.NodeAnalyzeFailedException;
import pipy.node.application.exception.UnprocessablePromptException;
import pipy.node.domain.NodeAnalyzer;
import pipy.node.presentation.dto.response.NodeAnalyzeResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NodeCommandService {

    private final NodeAnalyzer nodeAnalyzer;

    public List<NodeAnalyzeResponse> analyzeNode(final String content) {
        try {
            return nodeAnalyzer.analyze(content)
                .map(NodeAnalyzeResponse::from)
                .collectList()
                .block();
        } catch (final PipyException exception) {
            throw mapToException(exception);
        }
    }

    private HttpResponseException mapToException(final PipyException exception) {
        return switch (exception) {
            case UnprocessablePromptException ex -> HttpResponseException.badRequest(ex.getMessage());
            case NodeAnalyzeFailedException ex -> HttpResponseException.internalServerError(ex.getMessage());
            default -> HttpResponseException.internalServerError("알 수 없는 노드 분석 오류입니다.");
        };
    }
}
