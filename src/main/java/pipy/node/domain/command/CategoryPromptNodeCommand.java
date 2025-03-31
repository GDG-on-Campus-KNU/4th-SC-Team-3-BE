package pipy.node.domain.command;

import lombok.Builder;
import lombok.Getter;
import pipy.project.domain.Project;

import java.util.List;

@Getter
public class CategoryPromptNodeCommand extends NodeCommand {

    private final String key;
    private final List<String> value;

    @Builder
    public CategoryPromptNodeCommand(
        final Long nodeId,
        final Long projectId,
        final String key,
        final List<String> value
    ) {
        super(nodeId, projectId);
        this.key = key;
        this.value = value;
    }
}