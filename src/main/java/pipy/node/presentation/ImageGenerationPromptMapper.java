package pipy.node.presentation;

import pipy.node.application.CategoryImageGenerationPrompt;
import pipy.node.application.ImageGenerationPrompt;
import pipy.node.application.TextImageGenerationPrompt;
import pipy.node.presentation.dto.request.CategoryPromptNodeRequest;
import pipy.node.presentation.dto.request.NodeRequest;
import pipy.node.presentation.dto.request.TextPromptNodeRequest;

public class ImageGenerationPromptMapper {

    public static ImageGenerationPrompt mapToPrompt(final NodeRequest _request) {
        if (_request instanceof TextPromptNodeRequest request) {
            return mapToPrompt(request);
        }
        if (_request instanceof CategoryPromptNodeRequest request) {
            return mapToPrompt(request);
        }
        throw new IllegalArgumentException("지원하지 않은 노드 유형입니다.");
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
}