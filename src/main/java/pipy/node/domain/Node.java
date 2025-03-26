package pipy.node.domain;

import pipy.project.domain.Project;

import java.util.UUID;

public abstract class Node {

    private UUID id;
    private NodeType type;
    private Project project;

    public Node(final NodeType type, final Project project) {
        this.id = UUID.randomUUID();
        this.type = type;
        this.project = project;
    }
}