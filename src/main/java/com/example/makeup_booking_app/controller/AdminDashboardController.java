package com.example.makeup_booking_app.controller;

import com.example.makeup_booking_app.dto.AdminDashboardData;
import com.example.makeup_booking_app.service.AdminDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminDashboardController {

    @Autowired
    private AdminDashboardService adminDashboardService;

    @GetMapping("/dashboard-data")
    public ResponseEntity<AdminDashboardData> getAdminDashboardData() {
        AdminDashboardData dashboardData = adminDashboardService.getAdminDashboardData();
        return new ResponseEntity<>(dashboardData, HttpStatus.OK);
    }
}
