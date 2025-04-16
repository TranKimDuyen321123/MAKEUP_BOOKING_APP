package com.example.makeup_booking_app.repositories;
import com.example.makeup_booking_app.models.Permission;
import com.example.makeup_booking_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PermissionReporsitory extends JpaRepository<Permission, Long> {
    List<Permission> findAllByUser(User user);
}
