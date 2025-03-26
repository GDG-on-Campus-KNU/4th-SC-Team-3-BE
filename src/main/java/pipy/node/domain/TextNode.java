package pipy.node.domain;

import lombok.Builder;
import pipy.project.domain.Project;

public class TextNode extends Node {

    private String content;

    @Builder
    public TextNode(final String content, final Project project) {
        super(NodeType.TEXT, project);
        this.content = content;
    }
}