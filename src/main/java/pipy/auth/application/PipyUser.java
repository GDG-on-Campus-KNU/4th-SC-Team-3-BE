package pipy.auth.application;

import org.springframework.security.oauth2.core.user.OAuth2User;
import pipy.member.domain.Member;

public interface PipyUser extends OAuth2User {
    Member getMember();
}
