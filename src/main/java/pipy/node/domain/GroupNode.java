package pipy.node.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pipy.project.domain.Project;

@Getter
@Entity
@Table(name = "group_node")
@DiscriminatorValue("group")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupNode extends Node {

    @Builder
    public GroupNode(
        final Long id,
        final Project project
    ) {
        super(id, project);
    }
}