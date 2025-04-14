package com.example.makeup_booking_app.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "appointment_time", nullable = false)
    private Instant appointmentTime;

    @ColumnDefault("'PENDING'")
    @Lob
    @Column(name = "status")
    private String status;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "price", nullable = false)
    private Double price;  // Thêm thuộc tính price

    public Appointment(){
    }

    public Appointment(String id, Instant appointmentTime, String status, Instant createdAt, String userId, Double price) {
        this.id = id;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.createdAt = createdAt;
        this.userId = userId;
        this.price = price;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Appointment{" +
                "id=" + id +
                ", appointmentTime=" + appointmentTime +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", userId=" + userId +
                ", price=" + price +
                '}';
    }

    public String getId() {
        return id;
    }

    public Instant getAppointmentTime() {
        return appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public Double getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAppointmentTime(Instant appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
