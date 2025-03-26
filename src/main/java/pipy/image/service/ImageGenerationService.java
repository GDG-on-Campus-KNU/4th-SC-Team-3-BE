package pipy.image.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ImageGenerationService {

    private final ImageGenerator imageGenerator;
    private final ImageSaver imageSaver;

    public Flux<String> generate(final String prompt) {
        return Flux.just("generate_image_start")
            .concatWith(imageGenerator.generate(prompt)
                .publishOn(Schedulers.boundedElastic())
                .map(imageSaver::save)
            .concatWith(Flux.just("generate_image_end")));
    }
}
