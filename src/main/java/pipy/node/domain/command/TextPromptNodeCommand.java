package pipy.node.domain.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TextPromptNodeCommand extends NodeCommand {

    private final String content;

    @Builder
    public TextPromptNodeCommand(
        final Long nodeId,
        final Long projectId,
        final String content
    ) {
        super(nodeId, projectId);
        this.content = content;
    }
}