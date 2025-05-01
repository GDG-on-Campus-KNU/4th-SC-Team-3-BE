package pipy.auth.application;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pipy.member.domain.Member;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@AllArgsConstructor
public class GoogleOAuth2User implements PipyUser {

    private Member member;
    private GoogleOAuth2UserInfo userInfo;

    @Override
    public String getName() {
        return this.member.getEmail();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return userInfo.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final String role = member.getRole().name();
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public Member getMember() {
        return member;
    }
}
