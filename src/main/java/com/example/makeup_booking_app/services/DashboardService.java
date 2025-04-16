package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.repositories.BookingRepository;
import com.example.makeup_booking_app.repositories.ArtistRepository;
import com.example.makeup_booking_app.repositories.PaymentRepository;
import com.example.makeup_booking_app.repositories.BranchRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BranchRepository branchRepository;

    public Map<String, Object> getDashboardSummary() {
        Map<String, Object> summary = new HashMap<>();

        // Thiết lập mốc thời gian bắt đầu và kết thúc trong ngày hôm nay
        ZoneId zone = ZoneId.systemDefault();
        LocalDate today = LocalDate.now();

        Instant startOfDay = today.atStartOfDay(zone).toInstant();
        Instant endOfDay = today.plusDays(1).atStartOfDay(zone).toInstant();

        // Đếm số lượng booking có appointmentTime trong khoảng thời gian của ngày hôm nay
        summary.put("totalBookingsToday", bookingRepository.countByAppointmentTimeBetween(startOfDay, endOfDay));
        summary.put("activeArtists", artistRepository.countByStatus("active"));
        // Lấy doanh thu hôm nay từ PaymentRepository
        BigDecimal todayRevenue = paymentRepository.getRevenueByDate(startOfDay, endOfDay);
        summary.put("todayRevenue", todayRevenue);
        summary.put("totalBranches", branchRepository.count());

        return summary;
    }
}
