package com.example.makeup_booking_app.repositories;

import org.springframework.stereotype.Repository;
import java.util.Set;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TokenBlacklistRepository {
    private final Set<String> blacklisted = Collections.newSetFromMap(new ConcurrentHashMap<>());

    /**
     * Lưu token vào blacklist
     */
    public void save(String token) {
        blacklisted.add(token);
    }

    /**
     * Kiểm tra token đã bị blacklist hay chưa
     */
    public boolean exists(String token) {
        return blacklisted.contains(token);
    }
}
