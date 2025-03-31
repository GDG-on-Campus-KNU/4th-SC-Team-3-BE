package pipy.node.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pipy.project.domain.Project;

import java.util.List;

@Entity
@Table(name = "category_node")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryPromptNode extends Node implements Groupable{

    @Column(name = "category_key")
    private String key;

    @Column(name = "category_value")
    private String value;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_node_id")
    private GroupNode group;

    @Builder
    public CategoryPromptNode(
        final Long id,
        final String key,
        final List<String> value,
        final Project project
    ) {
        super(id, project);
        this.key = key;
        this.value = String.join(",", value);
    }

    public List<String> getValue() {
        return List.of(value.split(","));
    }

    @Override
    public void groupTo(GroupNode group) {
        this.group = group;
    }
}