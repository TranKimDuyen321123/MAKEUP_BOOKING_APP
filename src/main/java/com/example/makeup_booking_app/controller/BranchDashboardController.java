package com.example.makeup_booking_app.controller;

import com.example.makeup_booking_app.dto.BranchDashboardData;
import com.example.makeup_booking_app.service.BranchDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branch")
public class BranchDashboardController {

    @Autowired
    private BranchDashboardService branchDashboardService;

    @GetMapping("/{branchId}/dashboard-data")
    public ResponseEntity<BranchDashboardData> getBranchDashboardData(@PathVariable String branchId) {
        BranchDashboardData dashboardData = branchDashboardService.getDashboardData(branchId);
        if (dashboardData != null) {
            return ResponseEntity.ok(dashboardData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
