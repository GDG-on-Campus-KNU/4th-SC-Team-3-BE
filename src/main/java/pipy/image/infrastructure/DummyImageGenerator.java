package pipy.image.infrastructure;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import pipy.image.domain.Image;
import pipy.image.service.ImageGenerator;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

@Service
public class DummyImageGenerator implements ImageGenerator {

    private static byte[] loadImage() {
        final Resource resource = new ClassPathResource("static/dummy.png");
        try {
            return Files.readAllBytes(resource.getFile().toPath());
        } catch (final IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public Mono<Image> generate(final String prompt) {
        final Image image = new Image(loadImage());
        return Mono.delay(Duration.ofSeconds(5))
            .thenReturn(image);
    }
}
