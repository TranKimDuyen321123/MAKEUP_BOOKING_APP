package com.example.makeup_booking_app.controller;

import com.example.makeup_booking_app.dto.SystemStatsResponse;
import com.example.makeup_booking_app.service.DashboardStatsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminStatsController {

    private final DashboardStatsService dashboardStatsService;

    public AdminStatsController(DashboardStatsService dashboardStatsService) {
        this.dashboardStatsService = dashboardStatsService;
    }

    @GetMapping("/system-stats")
    public SystemStatsResponse getSystemStats() {
        return dashboardStatsService.getSystemStats();
    }
}
