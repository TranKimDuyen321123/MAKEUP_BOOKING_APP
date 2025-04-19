package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.models.User;
import com.example.makeup_booking_app.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import org.springframework.security.access.AccessDeniedException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @Autowired
    private UserRepository userRepository;


//    Nếu là ADMIN: gọi getFullSummary() → thống kê toàn hệ thống.
//
//    Nếu là MANAGER: gọi getSummaryForBranch(branchId) → thống kê theo chi nhánh của Manager.
//
//    Nếu không phải 2 vai trò trên → báo lỗi truy cập.


    public Map<String, Object> getDashboardSummary(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (!optionalUser.isPresent()) {
            throw new AccessDeniedException("User not found");
        }

        User user = optionalUser.get();

        if (user.hasRole("ADMIN")) {
            return getFullSummary(); // dữ liệu toàn hệ thống
        } else if (user.hasRole("MANAGER")) {
            Long branchId = user.getBranch().getId();
            return getSummaryForBranch(branchId); // chỉ dữ liệu chi nhánh của manager
        } else {
            throw new AccessDeniedException("You are not authorized to view the dashboard");
        }
    }


    //getSummaryForBranch(Long branchId) – Dành cho MANAGER
    private Map<String, Object> getSummaryForBranch(Long branchId) {
        Map<String, Object> summary = new HashMap<>();

        ZoneId zone = ZoneId.systemDefault();
        LocalDate today = LocalDate.now();
        Instant startOfDay = today.atStartOfDay(zone).toInstant();
        Instant endOfDay = today.plusDays(1).atStartOfDay(zone).toInstant();

        summary.put("totalBookingsToday", bookingRepository.countByAppointmentTimeBetweenAndBranchId(startOfDay, endOfDay, branchId));
        summary.put("activeArtists", artistRepository.countByStatusAndBranchId("active", branchId));
        BigDecimal todayRevenue = paymentRepository.getRevenueByDateAndBranch(startOfDay, endOfDay, branchId);
        summary.put("todayRevenue", todayRevenue);

        return summary;
    }

    //getFullSummary() – Dành cho ADMIN
    private Map<String, Object> getFullSummary() {
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