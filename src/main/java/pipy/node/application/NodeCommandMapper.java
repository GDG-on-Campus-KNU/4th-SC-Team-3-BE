package pipy.node.application;

import pipy.node.presentation.dto.request.CategoryPromptNodeRequest;
import pipy.node.presentation.dto.request.GroupNodeRequest;
import pipy.node.presentation.dto.request.NodeRequest;
import pipy.node.presentation.dto.request.TextPromptNodeRequest;
import pipy.node.domain.command.CategoryPromptNodeCommand;
import pipy.node.domain.command.GroupNodeCommand;
import pipy.node.domain.command.NodeCommand;
import pipy.node.domain.command.TextPromptNodeCommand;

import java.util.List;

public class NodeCommandMapper {

    public static NodeCommand mapToCommand(final NodeRequest _request) {
        if (_request instanceof TextPromptNodeRequest request) {
            return mapToCommand(request);
        }
        if (_request instanceof CategoryPromptNodeRequest request) {
            return mapToCommand(request);
        }
        if (_request instanceof GroupNodeRequest request) {
            return mapToCommand(request);
        }
        throw new IllegalArgumentException("지원하지 않은 노드 유형입니다.");
    }

    private static NodeCommand mapToCommand(final TextPromptNodeRequest request) {
        return TextPromptNodeCommand.builder()
            .nodeId(request.getNodeId())
            .content(request.getContent())
            .projectId(request.getProjectId())
            .build();
    }

    private static NodeCommand mapToCommand(final CategoryPromptNodeRequest request) {
        return CategoryPromptNodeCommand.builder()
            .nodeId(request.getNodeId())
            .key(request.getKey())
            .value(request.getValue())
            .projectId(request.getProjectId())
            .build();
    }

    private static NodeCommand mapToCommand(final GroupNodeRequest request) {
        final List<NodeCommand> commands = request.getContents()
            .stream()
            .map(NodeCommandMapper::mapToCommand)
            .toList();
        return GroupNodeCommand.builder()
            .nodeId(request.getNodeId())
            .contents(commands)
            .projectId(request.getProjectId())
            .build();
    }
}
