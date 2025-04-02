package com.example.makeupbooking.controller;

import com.example.makeupbooking.model.User;
import com.example.makeupbooking.model.Appointment;
import com.example.makeupbooking.service.UserService;
import com.example.makeupbooking.service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable; // Missing import

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppointmentService appointmentService;

    // API lấy danh sách tất cả người dùng
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // API lấy thông tin một người dùng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // API cập nhật thông tin người dùng
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            return ResponseEntity.ok(userService.saveUser(user));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // API xóa người dùng
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.ok("Người dùng đã được xóa.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // API lấy lịch sử đặt lịch của khách hàng
    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<Appointment>> getUserAppointments(@PathVariable Long id) {
        List<Appointment> appointments = appointmentService.getAppointmentsByUserId(id);
        return ResponseEntity.ok(appointments);
    }

    // API tìm kiếm người dùng
    @GetMapping("/search")
    public Page<User> searchUsers(@RequestParam(defaultValue = "") String keyword, Pageable pageable) {
        return userService.searchUsers(keyword, pageable);
    }
}
