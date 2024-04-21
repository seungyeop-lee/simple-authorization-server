package simpleauthorizationserver.backend.authserver.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import simpleauthorizationserver.backend.authserver.app.MemberService;
import simpleauthorizationserver.backend.authserver.auth.internal.MemberAuthenticationProvider;
import simpleauthorizationserver.backend.authserver.auth.social.AuthenticationResultConverter;
import simpleauthorizationserver.backend.authserver.auth.social.OAuth2LoginSuccessListener;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(c -> c
                .requestMatchers(
                        "/error",
                        "/favicon.ico",
                        "/page/**",
                        "/client/**",
                        "/signup"
                ).permitAll()
                .anyRequest().authenticated()
        );

        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(c -> c
                .loginProcessingUrl("/signin")
                .defaultSuccessUrl("/authenticated")
                .usernameParameter("email")
                // 로그인 실패 시 리다이렉션 방지
                .failureHandler((request, response, exception) -> response.setStatus(HttpServletResponse.SC_BAD_REQUEST))
        );

        http.oauth2Login(c -> c
                .addObjectPostProcessor(new ObjectPostProcessor<OAuth2LoginAuthenticationFilter>() {
                    @Override
                    public <O extends OAuth2LoginAuthenticationFilter> O postProcess(O object) {
                        object.setAuthenticationResultConverter(new AuthenticationResultConverter());
                        return object;
                    }
                })
        );

        http.logout(c -> c
                // 로그아웃 완료 시 리다이렉션 방지
                .logoutSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))
        );

        http.exceptionHandling(e -> e
                // 인증되지 않은 사용자의 요청 처리
                .authenticationEntryPoint((request, response, authException) -> response.setStatus(HttpServletResponse.SC_UNAUTHORIZED))
                // 인가되지 않은 사용자의 요청 처리
                .accessDeniedHandler((request, response, accessDeniedException) -> response.setStatus(HttpServletResponse.SC_FORBIDDEN))
        );

        return http.build();
    }

    @Bean
    public MemberAuthenticationProvider memberAuthenticationProvider() {
        return new MemberAuthenticationProvider(memberService, passwordEncoder);
    }

    @Bean
    public OAuth2LoginSuccessListener memberOAuth2LoginSuccessListener() {
        return new OAuth2LoginSuccessListener(memberService);
    }
}
