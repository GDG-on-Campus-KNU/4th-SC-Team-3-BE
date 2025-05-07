package pipy.node.application;

import java.util.List;

public record GroupImageGenerationPrompt(
    List<ImageGenerationPrompt> contents
) implements ImageGenerationPrompt {
}