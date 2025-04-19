package com.example.makeup_booking_app.repositories;
import com.example.makeup_booking_app.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomer_Id(Long userId);
    List<Booking> findByArtist_Id(Long userid);
    List<Booking> findByBranch_Id(Long id);
    //dem lươn booking
//    long countByBookingDate(LocalDate bookingDate);
    // Đếm số lượng booking trong khoảng thời gian
    long countByAppointmentTimeBetween(Instant start, Instant end);
    long countByAppointmentTimeBetweenAndBranchId(Instant start, Instant end, Long branchId);

} 

