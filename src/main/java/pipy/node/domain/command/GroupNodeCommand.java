package pipy.node.domain.command;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GroupNodeCommand extends NodeCommand {

    private final List<NodeCommand> contents;

    @Builder
    public GroupNodeCommand(
        final Long nodeId,
        final Long projectId,
        final List<NodeCommand> contents
    ) {
        super(nodeId, projectId);
        this.contents = contents;
    }
}