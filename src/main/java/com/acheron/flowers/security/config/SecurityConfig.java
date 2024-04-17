package com.acheron.flowers.security.config;

import com.acheron.flowers.security.jwt.JwtFilter;
import com.acheron.flowers.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.server.CookieSameSiteSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;
    private final JwtFilter jwtFilter;

//    @Value("${spring.allowed-cors}")
//    private String cors;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:5173").allowedOriginPatterns("*");
            }
        };
    }

    @SneakyThrows
    @Bean
    public SecurityFilterChain getFilterChain(HttpSecurity http) {
        return http.
                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
//                csrf(AbstractHttpConfigurer::disable).
        csrf(csrfConf -> csrfConf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).
        csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler())).
                cors(Customizer.withDefaults()).
                authorizeHttpRequests(request ->
                        request.requestMatchers("/api/v2/login", "/api/v2/registration", "/api/v1/permit", "/api/v2/csrf").
                                permitAll().
//                                requestMatchers("/api/v2/asd","/getUserData").
//                                hasAuthority(Role.USER.getAuthority()).
        anyRequest().
                                authenticated()).
                userDetailsService(userService).
                addFilterBefore(new CsrfCookieFilter(), UsernamePasswordAuthenticationFilter.class).
                addFilterBefore(jwtFilter, CsrfCookieFilter.class).
                build();
    }

    @Bean
    public CookieSameSiteSupplier applicationCookieSameSiteSupplier() {
        return CookieSameSiteSupplier.ofStrict();
    }
}
