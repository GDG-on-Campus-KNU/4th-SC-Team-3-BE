package pipy.node.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pipy.node.domain.command.GroupNodeCommand;
import pipy.node.domain.command.NodeCommand;
import pipy.project.domain.Project;
import pipy.project.domain.ProjectReader;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GroupNodeWriter implements NodeWriter {

    private final ProjectReader projectReader;
    private final List<NodeWriter> writers;

    @Override
    public boolean supports(final NodeCommand command) {
        return command.getClass().equals(GroupNodeCommand.class);
    }

    private NodeWriter getSupportsNodeWriter(final NodeCommand command) {
        return writers.stream()
            .filter(writer -> writer.supports(command))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 노드 유형입니다."));
    }

    private Groupable toGroupable(final Node node) {
        if (node instanceof Groupable groupable) {
            return groupable;
        }
        throw new IllegalArgumentException("그룹으로 묶을 수 없는 노드 유형입니다.");
    }

    @Override
    public Node write(final NodeCommand _command) {
        final GroupNodeCommand command = (GroupNodeCommand) _command;
        final Project project = projectReader.readById(command.getProjectId());
        final GroupNode group = GroupNode.builder()
            .id(command.getNodeId())
            .project(project)
            .build();
        final NodeWriter writer = getSupportsNodeWriter(command.getContents().getFirst());
        final List<Groupable> nodes = command.getContents().stream()
            .map(writer::write)
            .map(this::toGroupable)
            .toList();
        nodes.forEach(node -> node.groupTo(group));
        return group;
    }
}
