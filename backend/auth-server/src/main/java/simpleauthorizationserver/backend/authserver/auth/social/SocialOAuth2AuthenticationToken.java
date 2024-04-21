package simpleauthorizationserver.backend.authserver.auth.social;

import org.springframework.security.oauth2.core.user.OAuth2User;

public interface SocialOAuth2AuthenticationToken extends OAuth2User {
    /**
     * Social Login 결과를 통해 얻은 Email 반환
     *
     * @return
     */
    String getEmail();

    /**
     * 내부 관리 UUID로 name을 재설정하기 위해 사용
     *
     * @param name
     */
    void setName(String name);
}
