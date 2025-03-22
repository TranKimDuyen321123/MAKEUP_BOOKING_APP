package com.example.makeup_booking_app;

import com.example.makeup_booking_app.models.User;
import com.example.makeup_booking_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Inject UserRepository

    // Lấy danh sách tất cả khách hàng
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Tìm khách hàng theo ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Tìm khách hàng theo email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Tạo mới hoặc cập nhật thông tin khách hàng
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Xóa khách hàng theo ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
