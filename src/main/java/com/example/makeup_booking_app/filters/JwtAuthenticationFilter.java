package com.example.makeup_booking_app.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.makeup_booking_app.jwt.JwtUtil;
import com.example.makeup_booking_app.services.UserService;
import com.example.makeup_booking_app.services.TokenBlacklistService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final TokenBlacklistService tokenBlacklistService;

    @Autowired
    public JwtAuthenticationFilter(JwtUtil jwtUtil, @Lazy UserService userService, TokenBlacklistService tokenBlacklistService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);// Extract the token


            //  Check if token is blacklisted
            if (tokenBlacklistService.isBlacklisted(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is blacklisted");
                return;
            }

            String username = jwtUtil.extractUsername(token); // Extract username from token

            // Check if the user is not already authenticated
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.loadUserByUsername(username);

                // Validate the token
                if (jwtUtil.validateToken(token, userDetails)) {
                    // Create an authentication object and set it in the SecurityContext
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        chain.doFilter(request, response); // Continue the filter chain
    }

}