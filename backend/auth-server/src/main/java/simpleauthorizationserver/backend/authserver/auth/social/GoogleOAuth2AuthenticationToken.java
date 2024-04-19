package simpleauthorizationserver.backend.authserver.auth.social;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class GoogleOAuth2AuthenticationToken implements SocialOAuth2AuthenticationToken {

    private final OAuth2User oAuth2User;

    public GoogleOAuth2AuthenticationToken(OAuth2LoginAuthenticationToken loginToken) {
        this.oAuth2User = loginToken.getPrincipal();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return oAuth2User.getAttribute("email");
    }
}
