package pipy.node.application;

import reactor.core.publisher.Mono;

public interface ImageGenerator {

    Mono<ImageSaveCommand> generate(String prompt);
}
