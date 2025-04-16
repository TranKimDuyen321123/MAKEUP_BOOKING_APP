package com.example.makeup_booking_app.repositories;

import com.example.makeup_booking_app.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByBookingId(Long bookingId);
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE p.createdAt >= :startOfDay AND p.createdAt < :endOfDay")
    BigDecimal getRevenueByDate(@Param("startOfDay") Instant startOfDay, @Param("endOfDay") Instant endOfDay);

}