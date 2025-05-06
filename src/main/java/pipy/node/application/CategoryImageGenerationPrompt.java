package pipy.node.application;

import java.util.List;

public record CategoryImageGenerationPrompt(
    String key,
    List<String> value
) implements ImageGenerationPrompt {
}