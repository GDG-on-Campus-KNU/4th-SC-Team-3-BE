package pipy.node.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pipy.edge.domain.Edge;
import pipy.project.domain.Project;

import java.util.List;

@Entity
@DiscriminatorColumn
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Node {

    @Id
    @Getter
    @Column(name = "node_id")
    private Long id;

    @ManyToOne
    @Getter
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "from", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Edge> connectedFrom;

    @OneToMany(mappedBy = "to", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Edge> connectedTo;

    public Node(
        final Long id,
        final Project project
    ) {
        this.id = id;
        this.project = project;
    }

    public void connectTo(final Node to) {
        final Edge edge = Edge.of(this, to);
        connectedTo.add(edge);
        to.connectedFrom.add(edge);
    }

    public List<Node> getConnectedFrom() {
        return connectedFrom.stream()
            .map(Edge::getFrom)
            .toList();
    }

    public List<Node> getConnectedTo() {
        return connectedTo.stream()
            .map(Edge::getTo)
            .toList();
    }
}