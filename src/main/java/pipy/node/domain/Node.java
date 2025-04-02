package pipy.node.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pipy.project.domain.Project;

@Getter
@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Node {

    @Id
    @Column(name = "node_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public Node(
        final Long id,
        final Project project
    ) {
        this.id = id;
        this.project = project;
    }
}