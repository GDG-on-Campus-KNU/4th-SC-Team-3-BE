package pipy.node.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pipy.global.PipyException;
import pipy.node.domain.NodeAnalyzeResult;
import pipy.node.domain.NodeAnalyzer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class AINodeAnalyzer implements NodeAnalyzer {

    private final WebClient webClient;

    @Override
    public Flux<NodeAnalyzeResult> analyze(final String content) {
        final NodeAnalyzeRequest request = new NodeAnalyzeRequest(content);
        return webClient.post()
            .uri("/analyze")
            .bodyValue(request)
            .retrieve()
            .onStatus(HttpStatusCode::is5xxServerError, response -> {
                log.warn("AI category analysis failed: {}", response);
                return Mono.error(PipyException.exception("AI category analysis failed"));
            })
            .bodyToMono(NodeAnalyzeResult[].class)
            .flatMapMany(Flux::fromArray)
            .filter(result -> !result.value().isEmpty());
    }

    private record NodeAnalyzeRequest(String content) {
    }
}


