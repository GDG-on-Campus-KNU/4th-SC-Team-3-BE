package pipy.node.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pipy.node.application.ImageGenerationPrompt;
import pipy.node.application.ImageGenerator;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AIImageGenerator implements ImageGenerator {

    private final WebClient webClient;

    @Override
    public Mono<byte[]> generate(final List<ImageGenerationPrompt> prompts) {
        return webClient.post()
            .uri("/generate/image")
            .bodyValue(prompts)
            .retrieve()
            .bodyToMono(byte[].class);
    }
}
