package pipy.node.application;

public record TextImageGenerationPrompt(
    String content
) implements ImageGenerationPrompt {
}