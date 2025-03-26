package pipy.image.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pipy.image.service.ImageGenerationService;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class ImageGenerationController {

    private final ImageGenerationService imageGenerationService;

    @PostMapping(value = "/generate/image", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateImage(@RequestParam final String prompt) {
        return imageGenerationService.generate(prompt);
    }
}
