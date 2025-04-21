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

    //them de khong bị loi controller
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User createUser(User user) {
        if (existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash())); // mã hóa trước khi lưu
        return userRepository.save(user);
    }


    public User registerUser(RegisterDTO registerDTO) {
        if (existsByUsername(registerDTO.getUsername())) {
            throw new RuntimeException("Username already exists!");
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPasswordHash(passwordEncoder.encode(registerDTO.getPassword())); // nhớ encode password!
        user.setRole(registerDTO.getRole());
        user.setEmail(registerDTO.getEmail()); //add them moe

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPasswordHash())
                .roles(user.getRole().toUpperCase())  // Đảm bảo là "ADMIN" hoặc "USER"
                .build();
    }

    //xoa id user bang tk admin
    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public void deleteUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        userOptional.ifPresentOrElse(
                userRepository::delete,
                () -> {
                    throw new RuntimeException("User not found");
                }
        );
    }
}
