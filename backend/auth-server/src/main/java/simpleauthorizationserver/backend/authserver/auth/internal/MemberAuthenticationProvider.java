package simpleauthorizationserver.backend.authserver.auth.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import simpleauthorizationserver.backend.authserver.app.Member;
import simpleauthorizationserver.backend.authserver.app.MemberService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class MemberAuthenticationProvider implements AuthenticationProvider {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal();

        Member member = memberService.findByEmail(email).orElseThrow(NotExistEmailException::new);
        String inputPassword = (String) authentication.getCredentials();
        if (!passwordEncoder.matches(inputPassword, member.getPassword())) {
            throw new NotMatchPasswordException();
        }

        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.authenticated(member.getUuid(), null, List.of());
        authenticationToken.setDetails(member);

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public static class NotExistEmailException extends AuthenticationException {
        public NotExistEmailException() {
            super("not exist email");
        }
    }

    public static class NotMatchPasswordException extends AuthenticationException {
        public NotMatchPasswordException() {
            super("not match password");
        }
    }
}
