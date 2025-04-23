package com.example.makeup_booking_app.service;

import com.example.makeup_booking_app.dto.AdminDashboardData;
import com.example.makeup_booking_app.model.Appointment;
import com.example.makeup_booking_app.model.Appointment.Status;
import com.example.makeup_booking_app.repository.AppointmentRepository;
import com.example.makeup_booking_app.repository.UserRepository;
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
        long newBookings = appointmentRepository.findByStatus(Status.PENDING).size();
        long completedBookings = appointmentRepository.findByStatus(Status.CONFIRMED).size();
        long cancelledBookings = appointmentRepository.findByStatus(Status.CANCELLED).size();

        AdminDashboardData dashboardData = new AdminDashboardData();
        dashboardData.setTotalUsers(totalUsers);
        dashboardData.setNewBookings(newBookings);
        dashboardData.setCompletedBookings(completedBookings);
        dashboardData.setCancelledBookings(cancelledBookings);

        return dashboardData;
    }
}
