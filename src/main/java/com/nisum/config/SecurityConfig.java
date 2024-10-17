package com.nisum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for managing API security settings.
 * <p>
 * This class configures HTTP security, disables CSRF (for JWT usage), and
 * specifies public and protected API endpoints. It uses Spring Security's
 * {@code SecurityFilterChain} to define authentication and authorization rules.
 * </p>
 *
 * @author avasquez
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures security filter chain for HTTP requests.
     * <p>
     * It disables CSRF protection (since JWT will be used) and allows specific
     * public endpoints (such as registration and Swagger docs) while requiring
     * authentication for other endpoints.
     * </p>
     *
     * @param http the {@code HttpSecurity} object to configure security.
     * @return a {@code SecurityFilterChain} bean with the configured security settings.
     * @throws Exception if there is an error during security configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers(headers -> headers.frameOptions().sameOrigin()) // H2 console
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/users/register",
                                "/v3/api-docs/**", // Swagger API docs
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/h2-console/**")
                        .permitAll()
                        .anyRequest().authenticated()  // All other requests must be authenticated
                );

        return http.build();
    }
}
