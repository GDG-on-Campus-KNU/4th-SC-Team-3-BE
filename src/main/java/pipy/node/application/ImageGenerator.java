package pipy.node.application;

import pipy.node.domain.Image;
import reactor.core.publisher.Mono;

public interface ImageGenerator {

    Mono<Image> generate(String prompt);
}
