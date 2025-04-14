package com.example.makeup_booking_app.repository;

import com.example.makeup_booking_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.time.Instant;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    static Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    boolean existsByUsername(String username);

    long countByRoleAndCreatedAtBetween(String role, Instant start, Instant end);

    long countByRole(String role);

    Optional<User> findByEmail(String email);
}