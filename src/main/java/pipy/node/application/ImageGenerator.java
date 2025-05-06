package pipy.node.application;

import reactor.core.publisher.Mono;

import java.util.List;

public interface ImageGenerator {

    Mono<byte[]> generate(List<ImageGenerationPrompt> request);
}
