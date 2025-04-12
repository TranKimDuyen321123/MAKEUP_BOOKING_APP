package com.example.makeup_booking_app.dto;

import java.util.List;

public class BranchDashboardData {
    private String branchName;
    private int bookingsToday;
    private List<ServiceStat> serviceStats;

    // Constructors
    public BranchDashboardData() {
    }

    public BranchDashboardData(String branchName, int bookingsToday, List<ServiceStat> serviceStats) {
        this.branchName = branchName;
        this.bookingsToday = bookingsToday;
        this.serviceStats = serviceStats;
    }

    // Getters and Setters
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getBookingsToday() {
        return bookingsToday;
    }

    public void setBookingsToday(int bookingsToday) {
        this.bookingsToday = bookingsToday;
    }

    public List<ServiceStat> getServiceStats() {
        return serviceStats;
    }

    public void setServiceStats(List<ServiceStat> serviceStats) {
        this.serviceStats = serviceStats;
    }
}