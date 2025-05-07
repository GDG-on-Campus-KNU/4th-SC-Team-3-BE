package pipy.node.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pipy.global.PipyException;
import pipy.node.application.ImageGenerationPrompt;
import pipy.node.application.ImageGenerator;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
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
            .onStatus(HttpStatusCode::is5xxServerError, response -> {
                log.warn("AI image generation failed: {}", response);
                return Mono.error(PipyException.exception("AI image generation failed"));
            })
            .bodyToMono(byte[].class);
    }
}
