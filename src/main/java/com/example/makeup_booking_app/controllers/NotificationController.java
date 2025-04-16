package com.example.makeup_booking_app.controllers;

import com.example.makeup_booking_app.models.Notification;
import com.example.makeup_booking_app.models.User;
import com.example.makeup_booking_app.services.NotificationService;
import com.example.makeup_booking_app.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;

    public NotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.createNotification(notification));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getNotifications(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "false") boolean unread) {

        User user = userService.getUserById(userId); // lấy User từ userId
        return ResponseEntity.ok(notificationService.getNotifications(user, unread));
    }

    @PutMapping("/mark-read/{notificationId}")
    public ResponseEntity<Notification> markAsRead(@PathVariable Long notificationId) {
        Notification updated = notificationService.markAsRead(notificationId);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @PutMapping("/mark-all-read/{userId}")
    public ResponseEntity<Void> markAllAsRead(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        notificationService.markAllAsRead(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok().build();
    }
}
