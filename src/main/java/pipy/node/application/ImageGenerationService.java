package pipy.node.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pipy.global.utils.WebpConverter;
import pipy.member.domain.Member;
import pipy.node.presentation.dto.response.ImageGenerationResponse;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageGenerationService {

    private final ImageGenerator imageGenerator;
    private final StorageManager storageManager;

    private static final String ERROR_MESSAGE = "프롬프트가 부족해 이미지를 생성할 수 없습니다. 원하는 이미지에 대해 조금 더 자세히 설명해 주세요.";

    public Flux<ImageGenerationResponse> generate(
        final Member member,
        final Long projectId,
        final List<ImageGenerationPrompt> prompts
    ) {
        return Flux.just(ImageGenerationResponse.start())
            .concatWith(imageGenerator.generate(prompts)
                .publishOn(Schedulers.boundedElastic())
                .map(image -> saveImage(member, projectId, image))
                .onErrorReturn(ImageGenerationResponse.error(ERROR_MESSAGE))
            .concatWith(Flux.just(ImageGenerationResponse.end())));
    }

    private String createFilename(final Member member, final Long projectId) {
        final String uuid = UUID.randomUUID().toString();
        return String.format("%d/projects/%d/images/%s", member.getId(), projectId, uuid);
    }

    private ImageGenerationResponse saveImage(
        final Member member,
        final Long projectId,
        final byte[] image
    ) {
        final byte[] webp = WebpConverter.convertToWebp(image);
        final String filename = createFilename(member, projectId);
        final StorageSaveCommand command = new StorageSaveCommand("image/webp", filename, webp);
        return ImageGenerationResponse.generated(storageManager.save(command));
    }
}
