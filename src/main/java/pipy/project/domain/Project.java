package pipy.project.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pipy.global.domain.BaseTimeEntity;
import pipy.member.domain.Member;

@Entity
@Getter
@Table(name = "project")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "thumbnail")
    private String thumbnail;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Member owner;

    @Column(name = "canvas", columnDefinition = "json")
    private String canvas;

    @Builder
    public Project(
        final Long id,
        final String name,
        final String thumbnail,
        final Member owner,
        final String canvas
    ) {
        this.id = id;
        this.name = name;
        this.thumbnail = thumbnail;
        this.owner = owner;
        this.canvas = canvas;
    }

    public void updateCanvas(final JsonPatch canvas) {
        final JsonNode original = JsonNodeFactory.instance.objectNode().textNode(this.canvas);
        try {
            final JsonNode patched = canvas.apply(original);
            this.canvas = patched.toString();
        } catch (final JsonPatchException exception) {
            throw new IllegalStateException("Failed to apply JSON patch", exception);
        }
    }
}
