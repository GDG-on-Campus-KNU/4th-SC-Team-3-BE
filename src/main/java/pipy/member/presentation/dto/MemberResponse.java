package pipy.member.presentation.dto;

import pipy.member.domain.Member;

public record MemberResponse(
    Long memberId,
    String picture,
    String name
) {

    public static MemberResponse from(final Member member) {
        return new MemberResponse(
            member.getId(),
            member.getPicture(),
            member.getName()
        );
    }
}
