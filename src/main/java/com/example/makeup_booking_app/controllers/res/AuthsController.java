package com.example.makeup_booking_app.controllers.res;


import java.util.stream.Collectors;

import com.example.makeup_booking_app.models.User;
import com.example.makeup_booking_app.services.TokenBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import com.example.makeup_booking_app.dtos.AuthResponse;
import com.example.makeup_booking_app.dtos.LoginRequest;
import com.example.makeup_booking_app.dtos.RegisterDTO;
import com.example.makeup_booking_app.jwt.JwtUtil;
import com.example.makeup_booking_app.services.UserService;

@RestController
@RequestMapping("/auths")
public class AuthsController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    //them logout
    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @PostMapping(value= "/login",produces = "application/json" )
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
            //Extract the role from UserDetails
            String role = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(","));
            String token = jwtUtil.generateToken(userDetails.getUsername(), role);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thông tin đăng nhập");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO) {
        try {
            User result = userService.registerUser(registerDTO);
            return ResponseEntity.ok("User registered successfully with username: " + result.getUsername());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }

    @GetMapping("/profile")
    public String getUserProfile(HttpServletRequest request) {
        // Lấy token từ header Authorization
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return "Missing or invalid token!";
        }

        // Cắt bỏ "Bearer " để lấy token thực sự
        String token = authHeader.substring(7);

        return "Token: " + token; // Test xem có nhận được token không
    }

    //logout
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return ResponseEntity
                    .badRequest()
                    .body("Missing or invalid Authorization header");
        }
        String token = header.substring(7);
        tokenBlacklistService.blacklistToken(token);
        return ResponseEntity.ok("Logged out successfully");
    }

}