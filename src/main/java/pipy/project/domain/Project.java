package pipy.project.domain;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pipy.global.domain.BaseTimeEntity;
import pipy.member.domain.Member;

import java.util.UUID;

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
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            final JsonNode original = objectMapper.readTree(this.canvas);
            final JsonNode patched = canvas.apply(original);
            this.canvas = patched.toString();
        } catch (final Exception exception) {
            throw new IllegalStateException("Failed to apply JSON patch", exception);
        }
    }

    public String createThumbnail() {
        final String uuid = UUID.randomUUID().toString();
        return String.format("%d/projects/%d/%s", owner.getId(), id, uuid);
    }

    public void updateThumbnail(final String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public boolean isOwner(final Member member) {
        return this.owner.getId().equals(member.getId());
    }

    public void updateName(final String name) {
        this.name = name;
    }
}
