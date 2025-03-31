package pipy.node.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pipy.node.domain.command.NodeCommand;
import pipy.node.domain.command.TextPromptNodeCommand;
import pipy.node.persistence.TextPromptNodeJpaRepository;
import pipy.project.domain.Project;
import pipy.project.domain.ProjectReader;

@Component
@RequiredArgsConstructor
public class TextPromptNodeWriter implements NodeWriter {

    private final ProjectReader projectReader;
    private final TextPromptNodeJpaRepository repository;

    @Override
    public boolean supports(final NodeCommand command) {
        return command.getClass().equals(TextPromptNodeCommand.class);
    }

    @Override
    public Node write(final NodeCommand _command) {
        final TextPromptNodeCommand command = (TextPromptNodeCommand) _command;
        final Project project = projectReader.readById(command.getProjectId());
        final TextPromptNode node = TextPromptNode.builder()
            .id(command.getNodeId())
            .content(command.getContent())
            .project(project)
            .build();
        return repository.save(node);
    }
}
