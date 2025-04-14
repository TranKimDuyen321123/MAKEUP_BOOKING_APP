package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.dtos.RegisterDTO;
import com.example.makeup_booking_app.models.User;
import com.example.makeup_booking_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
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

    // 🧩 Thêm method registerUser vào đây
    public User registerUser(RegisterDTO registerDTO) {
        if (existsByUsername(registerDTO.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPasswordHash(passwordEncoder.encode(registerDTO.getPassword())); // nhớ encode password!
        user.setRole(registerDTO.getRole());

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPasswordHash())
                .roles(user.getRole())
                .build();
    }
}
