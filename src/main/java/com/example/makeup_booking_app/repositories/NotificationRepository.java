package com.example.makeup_booking_app.repositories;

import com.example.makeup_booking_app.models.Notification;
import com.example.makeup_booking_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserOrderByCreatedAtDesc(User user);
    List<Notification> findByUserAndStatusOrderByCreatedAtDesc(User user, String status);
    Long countByUserAndStatus(User user, String status);
}

