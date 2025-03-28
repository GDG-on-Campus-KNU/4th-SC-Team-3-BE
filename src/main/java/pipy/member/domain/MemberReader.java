package pipy.member.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pipy.member.persistence.MemberJpaRepository;

@Component
@RequiredArgsConstructor
public class MemberReader {

    private final MemberJpaRepository repository;

    public Member readByEmail(final String email) {
        return repository.findByEmail(email)
            .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
    }

    public boolean existsByEmail(final String email) {
        return repository.existsByEmail(email);
    }
}
