package pipy.node.infrastructure;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import pipy.node.application.ImageSaveCommand;
import pipy.node.application.ImageGenerator;
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
    public Mono<ImageSaveCommand> generate(final String prompt) {
        final ImageSaveCommand imageSaveCommand = new ImageSaveCommand(
            "image/png",
            "dummy.png",
            loadImage()
        );
        return Mono.delay(Duration.ofSeconds(5))
            .thenReturn(imageSaveCommand);
    }
}
