package pipy.node.domain.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class NodeCommand {

    private Long nodeId;
    private Long projectId;
}