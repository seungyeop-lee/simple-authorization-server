package simpleauthorizationserver.backend.authserver.auth.social;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;
import simpleauthorizationserver.backend.authserver.app.Member;
import simpleauthorizationserver.backend.authserver.app.MemberService;

@Slf4j
@RequiredArgsConstructor
public class OAuth2LoginSuccessListener {

    private final MemberService memberService;

    /**
     * OAuth2 로그인 성공 시, 회원이 아니면 회원가입을 자동으로 한 후, 로그인 처리한다.
     *
     * @param event
     */
    @EventListener
    @Transactional
    public void handleOAuth2LoginSuccess(InteractiveAuthenticationSuccessEvent event) {
        if (event.getGeneratedBy() != OAuth2LoginAuthenticationFilter.class) {
            return;
        }

        if (event.getAuthentication().getPrincipal() instanceof SocialOAuth2AuthenticationToken t) {
            memberService.findByEmail(t.getEmail())
                    .ifPresentOrElse(
                            member -> t.setName(member.getUuid()),
                            () -> {
                                Member member = memberService.joinFromSocial(t.getEmail());
                                t.setName(member.getUuid());
                            }
                    );
        }
    }
}
