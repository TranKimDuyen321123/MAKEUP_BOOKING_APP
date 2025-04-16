package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.models.Notification;
import com.example.makeup_booking_app.models.User;
import com.example.makeup_booking_app.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;


    public Notification createNotification(Notification notification) {
        notification.setStatus("UNREAD");
        notification.setCreatedAt(Instant.now());
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotifications(User user, boolean unread) {
        return unread
                ? notificationRepository.findByUserAndStatusOrderByCreatedAtDesc(user, "UNREAD")
                : notificationRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public Notification markAsRead(Long notificationId) {
        return notificationRepository.findById(notificationId).map(notification -> {
            notification.setStatus("READ");
            return notificationRepository.save(notification);
        }).orElse(null);
    }

    public void markAllAsRead(User user) {
        List<Notification> notifications = notificationRepository.findByUserAndStatusOrderByCreatedAtDesc(user, "UNREAD");
        notifications.forEach(notification -> notification.setStatus("READ"));
        notificationRepository.saveAll(notifications);
    }

    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }
}
