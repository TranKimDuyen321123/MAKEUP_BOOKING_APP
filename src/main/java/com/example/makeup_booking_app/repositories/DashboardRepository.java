package com.example.makeup_booking_app.repositories;

import com.example.makeup_booking_app.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DashboardRepository {

    public interface BookingRepository extends JpaRepository<Booking, Long> {
        long countByBookingDate(LocalDate bookingDate);
    }
}