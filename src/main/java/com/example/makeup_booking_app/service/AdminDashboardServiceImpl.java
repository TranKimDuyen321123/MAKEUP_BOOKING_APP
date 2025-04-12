package com.example.makeup_booking_app.service;

import com.example.makeup_booking_app.dto.AdminDashboardData;
import com.example.makeup_booking_app.repository.AppointmentRepository;
import com.example.makeup_booking_app.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardServiceImpl implements AdminDashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public AdminDashboardData getAdminDashboardData() {
        long totalUsers = userRepository.count();
        // Giả sử bạn có trường 'status' trong bảng 'appointments'
        long newBookings = appointmentRepository.countByStatus("PENDING");
        long completedBookings = appointmentRepository.countByStatus("COMPLETED");
        long cancelledBookings = appointmentRepository.countByStatus("CANCELLED");

        AdminDashboardData dashboardData = new AdminDashboardData();
        dashboardData.setTotalUsers(totalUsers);
        dashboardData.setNewBookings(newBookings);
        dashboardData.setCompletedBookings(completedBookings);
        dashboardData.setCancelledBookings(cancelledBookings);

        return dashboardData;
    }
}