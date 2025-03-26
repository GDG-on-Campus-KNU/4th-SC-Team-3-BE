package pipy.node.domain;

import lombok.Builder;
import pipy.project.domain.Project;

public class CategoryNode extends Node {

    private String key;
    private String value;

    @Builder
    public CategoryNode(final String key, final String value, final Project project) {
        super(NodeType.CATEGORY, project);
        this.key = key;
        this.value = value;
    }
}