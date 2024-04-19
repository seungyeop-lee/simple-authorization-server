package simpleauthorizationserver.backend.authserver.auth.social;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
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
    public void handleOAuth2LoginSuccess(InteractiveAuthenticationSuccessEvent event) {
        if (event.getGeneratedBy() != OAuth2LoginAuthenticationFilter.class) {
            return;
        }

        Authentication authentication = event.getAuthentication();
        log.info("OAuth2 login success: {}", authentication.getName());

        if (memberService.findByEmail(authentication.getName()).isEmpty()) {
            memberService.joinFromSocial(authentication.getName());
        }
    }
}
