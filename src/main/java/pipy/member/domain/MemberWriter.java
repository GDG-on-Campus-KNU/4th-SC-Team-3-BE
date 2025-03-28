package pipy.member.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pipy.member.persistence.MemberJpaRepository;

@Component
@RequiredArgsConstructor
public class MemberWriter {

    private final MemberJpaRepository repository;

    public Member write(final Member member) {
        return repository.save(member);
    }
}
