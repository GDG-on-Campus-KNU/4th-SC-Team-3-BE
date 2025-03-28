package pipy.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import pipy.member.domain.*;

@Service
@RequiredArgsConstructor
public class GoogleOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberReader memberReader;
    private final MemberWriter memberWriter;

    @Override
    public OAuth2User loadUser(final OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        final OAuth2User user = super.loadUser(userRequest);
        final GoogleOAuth2UserInfo userInfo = new GoogleOAuth2UserInfo(user.getAttributes());

        final String email = userInfo.getEmail();
        final String picture = userInfo.getPicture();
        final String name = userInfo.getName();
        final MemberInfo memberInfo = new MemberInfo(picture, name);

        final Member member = updateOrWrite(email, memberInfo);

        return new GoogleOAuth2User(member, userInfo);
    }

    private Member updateOrWrite(final String email, final MemberInfo info) {
        if (memberReader.existsByEmail(email)) {
            final Member member = memberReader.readByEmail(email);
            member.update(info);
            return member;
        }
        final Member member = Member.builder()
            .email(email)
            .info(info)
            .role(MemberRole.ROLE_USER)
            .build();
        return memberWriter.write(member);
    }
}
