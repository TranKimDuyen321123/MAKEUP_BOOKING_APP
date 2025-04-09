package com.example.makeup_booking_app.dtos;

import lombok.NoArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class AuthResponse {
    private String token;
    public AuthResponse(String token) {
        this.token = token;
    }
    // Getter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}