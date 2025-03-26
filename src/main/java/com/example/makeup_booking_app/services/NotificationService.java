package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.models.Notification;
import com.example.makeup_booking_app.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Notification createNotification(Notification notification) {
        notification.setRead(false);
        notification.setCreatedAt(new Date());
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotifications(Long userId, boolean unread) {
        return unread
                ? notificationRepository.findByRecipientIdAndReadFalseOrderByCreatedAtDesc(userId)
                : notificationRepository.findByRecipientIdOrderByCreatedAtDesc(userId);
    }

    public Notification markAsRead(Long notificationId) {
        return notificationRepository.findById(notificationId).map(notification -> {
            notification.setRead(true);
            return notificationRepository.save(notification);
        }).orElse(null);
    }

    public void markAllAsRead(Long userId) {
        List<Notification> notifications = notificationRepository.findByRecipientIdAndReadFalseOrderByCreatedAtDesc(userId);
        notifications.forEach(notification -> notification.setRead(true));
        notificationRepository.saveAll(notifications);
    }

    public void deleteNotification(Long notificationId) {
        notificationRepository.deleteById(notificationId);
    }
}
