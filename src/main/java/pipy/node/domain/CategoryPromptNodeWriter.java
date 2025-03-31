package pipy.node.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pipy.node.domain.command.CategoryPromptNodeCommand;
import pipy.node.domain.command.NodeCommand;
import pipy.node.persistence.CategoryPromptNodeJpaRepository;
import pipy.project.domain.Project;
import pipy.project.domain.ProjectReader;

@Component
@RequiredArgsConstructor
public class CategoryPromptNodeWriter implements NodeWriter {

    private final ProjectReader projectReader;
    private final CategoryPromptNodeJpaRepository repository;

    @Override
    public boolean supports(final NodeCommand command) {
        return command.getClass().equals(CategoryPromptNodeCommand.class);
    }

    @Override
    public Node write(final NodeCommand _command) {
        final CategoryPromptNodeCommand command = (CategoryPromptNodeCommand) _command;
        final Project project = projectReader.readById(command.getProjectId());
        final CategoryPromptNode node = CategoryPromptNode.builder()
            .id(command.getNodeId())
            .key(command.getKey())
            .value(command.getValue())
            .project(project)
            .build();
        return repository.save(node);
    }
}
