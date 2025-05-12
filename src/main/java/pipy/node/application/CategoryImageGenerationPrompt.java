package pipy.node.application;

import lombok.Getter;

import java.util.List;

@Getter
public class CategoryImageGenerationPrompt extends ImageGenerationPrompt {

    private final String key;
    private final List<String> value;

    public CategoryImageGenerationPrompt(String key, List<String> value) {
        super("category");
        this.key = key;
        this.value = value;
    }
}