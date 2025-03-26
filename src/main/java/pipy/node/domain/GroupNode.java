package pipy.node.domain;

import lombok.Builder;
import pipy.project.domain.Project;

import java.util.List;

public class GroupNode extends Node {

    private List<CategoryNode> contents;

    @Builder
    public GroupNode(final List<CategoryNode> contents, final Project project) {
        super(NodeType.GROUP, project);
        this.contents = contents;
    }
}