package simpleauthorizationserver.backend.authserver.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import simpleauthorizationserver.backend.authserver.config.jsonlogin.JsonLoginConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(c -> c
                .requestMatchers(
                        "index.html",
                        "favicon.ico",
                        "/page/**",
                        "/assets/**"
                ).permitAll()
                .anyRequest().authenticated()
        );

        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(AbstractHttpConfigurer::disable);

        http.with(new JsonLoginConfigurer<>(), c -> c
                .loginProcessingUrl("/signin")
                .defaultSuccessUrl("/authenticated")
                // 로그인 실패 시 리다이렉션 방지
                .failureHandler((request, response, exception) -> response.setStatus(HttpServletResponse.SC_BAD_REQUEST))
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
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
