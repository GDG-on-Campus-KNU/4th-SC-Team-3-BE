package pipy.project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pipy.member.domain.Member;

@Entity
@Getter
@Table(name = "project")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Member owner;

    @Builder
    public Project(
        final Long id,
        final Member owner
    ) {
        this.id = id;
        this.owner = owner;
    }
}
