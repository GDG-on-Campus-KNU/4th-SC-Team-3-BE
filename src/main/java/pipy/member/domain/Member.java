package pipy.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Getter
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "email")
    private String email;

    @Embedded
    private MemberInfo info;

    @Getter
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Builder
    public Member (
        final String email,
        final MemberInfo info,
        final MemberRole role
    ) {
        this.email = email;
        this.info = info;
        this.role = role;
    }

    public String getPicture() {
        return info.getPicture();
    }

    public String getName() {
        return info.getName();
    }

    public void update(final MemberInfo info) {
        this.info = info;
    }
}
