package pipy.node.application;

import lombok.Getter;

import java.util.List;

@Getter
public class GroupImageGenerationPrompt extends ImageGenerationPrompt {

    private final List<ImageGenerationPrompt> contents;

    public GroupImageGenerationPrompt(List<ImageGenerationPrompt> contents) {
        super("group");
        this.contents = contents;
    }
}