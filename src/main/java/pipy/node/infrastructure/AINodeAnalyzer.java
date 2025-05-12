package pipy.node.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pipy.node.application.exception.UnprocessablePromptException;
import pipy.node.domain.NodeAnalyzeResult;
import pipy.node.domain.NodeAnalyzer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AINodeAnalyzer implements NodeAnalyzer {

    private final WebClient webClient;
    private final ObjectMapper mapper;

    @Override
    public Flux<NodeAnalyzeResult> analyze(final String content) {
        final NodeAnalyzeRequest request = new NodeAnalyzeRequest(content);
        return webClient.post()
            .uri("/analyze")
            .bodyValue(request)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(String.class)
                .flatMap(body -> {
                    log.warn("카테고리 분석을 할 수 없는 프롬프트입니다.\n[요청]\n{}\n[응답]\n{}", mapToString(request), body);
                    return Mono.error(new UnprocessablePromptException());
                })
            )
            .onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
                .flatMap(body -> {
                    log.warn("카테고리 분석 시 오류가 발생했습니다.\n[요청]\n{}\n[응답]\n{}", mapToString(request), body);
                    return Mono.error(new UnprocessablePromptException());
                })
            )
            .bodyToMono(NodeAnalyzeResult[].class)
            .flatMapMany(Flux::fromArray)
            .filter(result -> !result.value().isEmpty());
    }

    @Getter
    private static class NodeAnalyzeRequest {

        private final String type = "text";
        private final String content;

        public NodeAnalyzeRequest(final String content) {
            this.content = content;
        }
    }

    private String mapToString(final NodeAnalyzeRequest request) {
        try {
            return mapper.writeValueAsString(request);
        } catch (final Exception exception) {
            log.error("프롬프트를 JSON으로 변환하는 중 오류가 발생했습니다.", exception);
            return "";
        }
    }
}


