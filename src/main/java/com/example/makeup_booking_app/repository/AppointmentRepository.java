package com.example.makeup_booking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import com.example.makeup_booking_app.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    // Các phương thức khác
    Long countByBranchIdAndAppointmentTimeBetween(String branchId, LocalDateTime startTime, LocalDateTime endTime);

    Long countByStatus(String status); // Phương thức đã được thêm
}