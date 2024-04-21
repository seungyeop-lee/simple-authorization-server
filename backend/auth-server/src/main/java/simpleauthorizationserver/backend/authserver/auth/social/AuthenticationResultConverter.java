package simpleauthorizationserver.backend.authserver.auth.social;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken;

@RequiredArgsConstructor
public class AuthenticationResultConverter implements Converter<OAuth2LoginAuthenticationToken, OAuth2AuthenticationToken> {
    
    /**
     * client registration 별 authentication token 생성
     *
     * @param authenticationResult the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return
     */
    @Override
    public OAuth2AuthenticationToken convert(OAuth2LoginAuthenticationToken authenticationResult) {
        return new OAuth2AuthenticationToken(
                convertToMemberOAuth2AuthenticationToken(authenticationResult),
                authenticationResult.getAuthorities(),
                authenticationResult.getClientRegistration().getRegistrationId()
        );
    }

    private static SocialOAuth2AuthenticationToken convertToMemberOAuth2AuthenticationToken(OAuth2LoginAuthenticationToken authenticationResult) {
        return switch (authenticationResult.getClientRegistration().getClientName()) {
            case "google" -> new GoogleOAuth2AuthenticationToken(authenticationResult);
            default -> throw new IllegalArgumentException("not supported client registration");
        };
    }
}
