package com.example.makeup_booking_app.services;
import com.example.makeup_booking_app.models.User;
import com.example.makeup_booking_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public abstract class UserService {
    @Autowired
    private UserRepository userRepository;
    public Optional<User> findByUsername(String username) {
        return UserRepository.findByUsername(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User createUser(User user) {
        if (existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }
        return userRepository.save(user);
    }
}

