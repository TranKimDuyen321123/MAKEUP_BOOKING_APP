package com.example.makeup_booking_app.dto;

public class AdminDashboardData {
    private long totalUsers;
    private long newBookings;
    private long completedBookings;
    private long cancelledBookings;

    // Constructor không tham số (bắt buộc cho Jackson để (de)serialize)
    public AdminDashboardData() {
    }

    // Constructor có tham số (tùy chọn, để tạo đối tượng dễ dàng hơn)
    public AdminDashboardData(long totalUsers, long newBookings, long completedBookings, long cancelledBookings) {
        this.totalUsers = totalUsers;
        this.newBookings = newBookings;
        this.completedBookings = completedBookings;
        this.cancelledBookings = cancelledBookings;
    }

    // Getters và Setters cho tất cả các trường
    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getNewBookings() {
        return newBookings;
    }

    public void setNewBookings(long newBookings) {
        this.newBookings = newBookings;
    }

    public long getCompletedBookings() {
        return completedBookings;
    }

    public void setCompletedBookings(long completedBookings) {
        this.completedBookings = completedBookings;
    }

    public long getCancelledBookings() {
        return cancelledBookings;
    }

    public void setCancelledBookings(long cancelledBookings) {
        this.cancelledBookings = cancelledBookings;
    }
}
