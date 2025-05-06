package pipy.node.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pipy.global.utils.WebpConverter;
import pipy.member.domain.Member;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageGenerationService {

    private final ImageGenerator imageGenerator;
    private final ImageSaver imageSaver;

    public Flux<String> generate(
        final Member member,
        final Long projectId,
        final List<ImageGenerationPrompt> prompts
    ) {
        return Flux.just("generate_image_start")
            .concatWith(imageGenerator.generate(prompts)
                .publishOn(Schedulers.boundedElastic())
                .map(image -> saveImage(member, projectId, image))
            .concatWith(Flux.just("generate_image_end")));
    }

    private String createFilename(final Member member, final Long projectId) {
        final String uuid = UUID.randomUUID().toString();
        return String.format("%d/projects/%d/images/%s", member.getId(), projectId, uuid);
    }

    private String saveImage(
        final Member member,
        final Long projectId,
        final byte[] image
    ) {
        final byte[] webp = WebpConverter.convertToWebp(image);
        final String filename = createFilename(member, projectId);
        final ImageSaveCommand command = new ImageSaveCommand("image/webp", filename, image);
        return imageSaver.save(command);
    }
}
