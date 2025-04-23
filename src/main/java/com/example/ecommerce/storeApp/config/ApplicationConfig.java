package com.example.ecommerce.storeApp.config;

import com.example.ecommerce.storeApp.modular.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // Tells Spring this is a configuration class
@RequiredArgsConstructor // Auto-generates constructor to inject dependencies (here: userRepository)
public class ApplicationConfig {

    // Injecting your user repository to fetch user data from the database
    private final UserRepository userRepository;

    // ✅ This bean tells Spring how to fetch user details (used in login)
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        // Looks up user by email, throws exception if not found
    }

    // ✅ This bean sets up how Spring will handle authentication
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService()); // uses our custom service
        authProvider.setPasswordEncoder(passwordEncoder()); // uses bcrypt to check password
        return authProvider;
    }

    // ✅ This bean returns the authentication manager (used to authenticate users)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // ✅ This bean defines the password encoder (how passwords are hashed and checked)
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // secure one-way hashing
    }
}
