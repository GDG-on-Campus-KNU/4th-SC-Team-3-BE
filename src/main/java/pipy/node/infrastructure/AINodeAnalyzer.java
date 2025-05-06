package pipy.node.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pipy.node.domain.NodeAnalyzeResult;
import pipy.node.domain.NodeAnalyzer;
import reactor.core.publisher.Flux;

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
            .bodyToMono(NodeAnalyzeResult[].class)
            .flatMapMany(Flux::fromArray);
    }

    private record NodeAnalyzeRequest(String content) {
    }
}


