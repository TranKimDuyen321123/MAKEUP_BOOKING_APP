package com.example.makeup_booking_app.controllers;

import com.example.makeup_booking_app.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")

    public class DashboardController {

        @Autowired
        private DashboardService dashboardService;

        @GetMapping("/summary")
        public ResponseEntity<Map<String, Object>> getDashboardSummary() {
            Map<String, Object> summary = dashboardService.getDashboardSummary();
            return ResponseEntity.ok(summary);
        }
    }