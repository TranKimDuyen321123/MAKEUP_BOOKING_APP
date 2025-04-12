package com.example.makeup_booking_app.dto;

public class ServiceStat {
    private String service;
    private int count;

    // Constructors
    public ServiceStat() {
    }

    public ServiceStat(String service, int count) {
        this.service = service;
        this.count = count;
    }

    // Getters and Setters
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}