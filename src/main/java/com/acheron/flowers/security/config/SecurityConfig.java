package com.acheron.flowers.security.config;

import com.acheron.flowers.security.entity.Role;
import com.acheron.flowers.security.jwt.JwtCookieFilter;
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
import org.springframework.security.web.csrf.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;
    private final JwtCookieFilter jwtFilter;

    @Value("${spring.allowed-cors}")
    private String cors;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("https://localhost:5173/", cors).
                        allowedOriginPatterns("*").exposedHeaders("*", "Set-Cookie", "X-XSRF-TOKEN")
                        .allowedMethods("GET","POST","PUT","PATCH","DELETE")
                        .allowCredentials(true);
            }
        };
    }


    @SneakyThrows
    @Bean
    public SecurityFilterChain getFilterChain(HttpSecurity http) {
        return http.
                sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                csrf(AbstractHttpConfigurer::disable).
//        csrf(csrfConf -> csrfConf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).
//        csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler()).
//        ignoringRequestMatchers("/api/v2/login","/api/v2/csrf")).
        cors(Customizer.withDefaults()).
                authorizeHttpRequests(request ->
                        request.requestMatchers(
//                                        "api/v2/logout",
                                "asd",
                                        "/api/v2/login", "/api/v2/registration",
                                        "/api/v2/csrf", "/swagger-ui/**", "/v3/api-docs/**").
                                permitAll().
                                requestMatchers("/api/v2/user/**", "/api/v1/permit").authenticated().
                                requestMatchers("/api/v2/admin/**").hasAuthority(Role.ADMIN.getAuthority()).
                                anyRequest().
                                authenticated()).
//                                permitAll()).
                userDetailsService(userService).
                addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).
//                addFilterBefore(new CsrfCookieFilter(), UsernamePasswordAuthenticationFilter.class).
//                addFilterAfter(jwtFilter, CsrfCookieFilter.class).
        build();
    }

    @Bean
    public CookieSameSiteSupplier applicationCookieSameSiteSupplier() {
        return CookieSameSiteSupplier.ofStrict();
    }
}
