package com.example.ecommerce.storeApp.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // ✅ Disable CSRF (we're using token-based security)
                .csrf(AbstractHttpConfigurer::disable)

                // ✅ Set session management to STATELESS (no HTTP sessions)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // ✅ Define which endpoints are public and which need auth
                .authorizeHttpRequests(auth -> auth
                        // Anyone can access /api/v1/auth/** (login, register, etc.)
                        .requestMatchers("/api/v1/auth/**").permitAll()

                        // All other requests require authentication
                        .anyRequest().authenticated()
                )

                // ✅ Set your custom authentication provider
                .authenticationProvider(authenticationProvider)

                // ✅ Add JWT filter BEFORE Spring's default login filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // ✅ Return the configured security chain
        return http.build();
    }
}
