package pipy.edge.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pipy.node.domain.Node;

@Entity
@Getter
@Table(name = "edge")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Edge {

    @Id
    @Column(name = "edge_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_node_id")
    private Node from;

    @ManyToOne
    @JoinColumn(name = "to_node_id")
    private Node to;

    public static Edge of(
        final Node from,
        final Node to
    ) {
        final Edge edge = new Edge();
        edge.from = from;
        edge.to = to;
        return edge;
    }
}
