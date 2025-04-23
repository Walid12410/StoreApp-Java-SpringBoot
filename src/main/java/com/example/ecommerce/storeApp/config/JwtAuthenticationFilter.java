package com.example.ecommerce.storeApp.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component  // Makes this filter a Spring bean
@RequiredArgsConstructor  // Auto-injects JwtService and UserDetailsService
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // Inject your custom JWT logic and user fetching logic
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;

        // ✅ If no Authorization header or it's not a Bearer token → skip filter
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // ✅ Extract token (remove "Bearer " prefix)
        jwtToken = authHeader.substring(7);

        // ✅ Get username (usually email) from JWT
        userEmail = jwtService.extractUsername(jwtToken);

        // ✅ If user is not already authenticated
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user from DB
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // ✅ Validate token with user info
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                // Create authentication token with user's details & roles
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // Attach request details
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // ✅ Set user as authenticated in Spring Security
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // ✅ Continue with next filter in chain (or reach controller)
        filterChain.doFilter(request, response);
    }
}
