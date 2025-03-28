package pipy.member.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import pipy.member.domain.Member;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}