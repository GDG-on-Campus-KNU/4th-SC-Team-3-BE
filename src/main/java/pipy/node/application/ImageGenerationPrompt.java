package pipy.node.application;

import lombok.Getter;

@Getter
public abstract class ImageGenerationPrompt {

    private final String type;

    public ImageGenerationPrompt(String type) {
        this.type = type;
    }
}