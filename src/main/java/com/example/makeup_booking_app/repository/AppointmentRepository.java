package com.example.makeup_booking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import com.example.makeup_booking_app.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    @Query("SELECT COUNT(a) FROM Appointment a WHERE DATE(a.appointmentTime) = CURRENT_DATE")
    long countAppointmentsForToday();

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.appointmentTime >= :startOfWeek AND a.appointmentTime < :endOfWeek")
    long countAppointmentsForThisWeek(Instant startOfWeek, Instant endOfWeek);

    @Query("SELECT SUM(a.price) FROM Appointment a WHERE DATE(a.appointmentTime) = CURRENT_DATE")
    Double sumPriceForToday();

    @Query("SELECT SUM(a.price) FROM Appointment a WHERE a.appointmentTime >= :startOfWeek AND a.appointmentTime < :endOfWeek")
    Double sumPriceForThisWeek(Instant startOfWeek, Instant endOfWeek);

    // Các phương thức khác
    Long countByBranchIdAndAppointmentTimeBetween(String branchId, LocalDateTime startTime, LocalDateTime endTime);

    Long countByStatus(String status);
}
