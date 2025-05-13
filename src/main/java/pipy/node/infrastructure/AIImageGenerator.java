package pipy.node.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pipy.node.application.ImageGenerationPrompt;
import pipy.node.application.ImageGenerator;
import pipy.node.application.exception.ImageGenerationFailedException;
import pipy.node.application.exception.UnprocessablePromptException;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AIImageGenerator implements ImageGenerator {

    private final ObjectMapper mapper;
    private final WebClient webClient;

    @Value("${pipy.ai.token}")
    private String token;

    @Override
    public Mono<byte[]> generate(final List<ImageGenerationPrompt> prompts) {
        return webClient.post()
            .uri("/generate/image")
            .header("X-Api-Key", token)
            .bodyValue(prompts)
            .retrieve()
            .onStatus(HttpStatusCode::is4xxClientError, response -> response.bodyToMono(String.class)
                .flatMap(body -> {
                    log.warn("이미지 생성을 할 수 없는 프롬프트입니다.\n[요청]\n{}\n[응답]\n{}", mapToString(prompts), body);
                    return Mono.error(new UnprocessablePromptException());
                })
            )
            .onStatus(HttpStatusCode::is5xxServerError, response -> response.bodyToMono(String.class)
                .flatMap(body -> {
                    log.warn("이미지 생성 시 오류가 발생했습니다.\n[요청]\n{}\n[응답]\n{}", mapToString(prompts), body);
                    return Mono.error(new ImageGenerationFailedException());
                })
            )
            .bodyToMono(byte[].class);
    }

    private String mapToString(final List<ImageGenerationPrompt> prompts) {
        try {
            return mapper.writeValueAsString(prompts);
        } catch (final Exception exception) {
            log.error("프롬프트를 JSON으로 변환하는 중 오류가 발생했습니다.", exception);
            return "";
        }
    }
}
