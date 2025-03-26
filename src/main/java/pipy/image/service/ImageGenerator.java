package pipy.image.service;

import pipy.image.domain.Image;
import reactor.core.publisher.Mono;

public interface ImageGenerator {

    Mono<Image> generate(String prompt);
}
