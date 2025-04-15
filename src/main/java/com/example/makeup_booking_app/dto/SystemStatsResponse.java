package com.example.makeup_booking_app.dto;

public class SystemStatsResponse {
    private long totalBranches;
    private long totalServices;
    private long totalArtists;
    private long totalAppointments;

    public SystemStatsResponse(long totalBranches, long totalServices, long totalArtists, long totalAppointments) {
        this.totalBranches = totalBranches;
        this.totalServices = totalServices;
        this.totalArtists = totalArtists;
        this.totalAppointments = totalAppointments;
    }

    public long getTotalBranches() {
        return totalBranches;
    }

    public long getTotalServices() {
        return totalServices;
    }

    public long getTotalArtists() {
        return totalArtists;
    }

    public long getTotalAppointments() {
        return totalAppointments;
    }
}
