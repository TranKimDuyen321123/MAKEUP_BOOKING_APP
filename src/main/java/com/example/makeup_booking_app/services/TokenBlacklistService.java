package com.example.makeup_booking_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.makeup_booking_app.repositories.TokenBlacklistRepository;

@Service
public class TokenBlacklistService {
    private final TokenBlacklistRepository repository;

    @Autowired
    public TokenBlacklistService(TokenBlacklistRepository repository) {
        this.repository = repository;
    }

    /**
     * Đưa token vào blacklist
     */
    public void blacklistToken(String token) {
        repository.save(token);
    }

    /**
     * Kiểm tra token đã nằm trong blacklist chưa
     */
    public boolean isBlacklisted(String token) {
        return repository.exists(token);
    }
}
