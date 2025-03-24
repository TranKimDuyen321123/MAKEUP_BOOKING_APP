package com.example.makeupbookingapp.controllers;

import com.example.makeupbookingapp.models.User;
import com.example.makeupbookingapp.services.EmailService;
import com.example.makeupbookingapp.services.NotificationService;
import com.example.makeupbookingapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final UserService userService;
    private final EmailService emailService;
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(UserService userService, EmailService emailService, NotificationService notificationService) {
        this.userService = userService;
        this.emailService = emailService;
        this.notificationService = notificationService;
    }

    @PostMapping("/reminder/{userId}")
    public ResponseEntity<String> sendReminder(@PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            // Gửi email
            emailService.sendEmail(user.get().getEmail(), "Lịch Hẹn Makeup", "Nhắc lịch hẹn...");

            // Gửi thông báo real-time WebSocket
            notificationService.sendNotification(userId, "Bạn có lịch hẹn makeup sắp tới!");

            return ResponseEntity.ok("Email & Real-time notification sent!");
        }
        return ResponseEntity.badRequest().body("User not found!");
    }
}
