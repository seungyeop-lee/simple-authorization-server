package simpleauthorizationserver.backend.authserver.app;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member join(String email, String password) {
        String encodePassword = passwordEncoder.encode(password);
        Member member = Member.forSignupOf(email, encodePassword);
        return memberRepository.save(member);
    }

    public Member joinFromSocial(String email) {
        Member member = Member.forSignupOf(email, null);
        return memberRepository.save(member);
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @PostConstruct
    public void createTestData() {
        if (memberRepository.count() == 0L) {
            join("user@example.com", "user");
        }
    }
}
