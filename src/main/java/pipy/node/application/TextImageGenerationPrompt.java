package pipy.node.application;

import lombok.Getter;

@Getter
public class TextImageGenerationPrompt extends ImageGenerationPrompt {

    private final String content;

    public TextImageGenerationPrompt(String content) {
        super("text");
        this.content = content;
    }
}