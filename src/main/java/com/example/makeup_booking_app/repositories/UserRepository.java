package com.example.makeup_booking_app.repositories;
import com.example.makeup_booking_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    static Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    boolean existsByUsername(String username);
}
