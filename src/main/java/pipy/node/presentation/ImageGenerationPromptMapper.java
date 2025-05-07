package pipy.node.presentation;

import pipy.node.application.CategoryImageGenerationPrompt;
import pipy.node.application.GroupImageGenerationPrompt;
import pipy.node.application.ImageGenerationPrompt;
import pipy.node.application.TextImageGenerationPrompt;
import pipy.node.presentation.dto.request.*;

public class ImageGenerationPromptMapper {

    public static ImageGenerationPrompt mapToPrompt(final NodeRequest request) {
        return switch (request) {
            case TextPromptNodeRequest text -> mapToPrompt(text);
            case CategoryPromptNodeRequest category -> mapToPrompt(category);
            case GroupNodeRequest group -> mapToPrompt(group);
            default -> throw new IllegalArgumentException("지원하지 않은 노드 유형입니다.");
        };
    }

    private static ImageGenerationPrompt mapToPrompt(final TextPromptNodeRequest request) {
        return new TextImageGenerationPrompt(
            request.getContent()
        );
    }

    private static ImageGenerationPrompt mapToPrompt(final CategoryPromptNodeRequest request) {
        return new CategoryImageGenerationPrompt(
            request.getKey(),
            request.getValue()
        );
    }

    private static ImageGenerationPrompt mapToPrompt(final GroupNodeRequest request) {
        return new GroupImageGenerationPrompt(
            request.getContents().stream()
                .map(ImageGenerationPromptMapper::mapToPrompt)
                .toList()
        );
    }
}