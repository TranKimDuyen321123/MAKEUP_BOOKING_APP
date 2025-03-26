package com.example.makeup_booking_app.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "customer_id", nullable = false)
    private com.example.makeup_booking_app.models.User customer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "service_id", nullable = false)
    private com.example.makeup_booking_app.models.Service service;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "branch_id", nullable = false)
    private com.example.makeup_booking_app.models.Branch branch;

    @Column(name = "appointment_time", nullable = false)
    private Instant appointmentTime;

    @ColumnDefault("'PENDING'")
    @Lob
    @Column(name = "status")
    private String status;

    @ColumnDefault("'UNPAID'")
    @Lob
    @Column(name = "payment_status")
    private String paymentStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.example.makeup_booking_app.models.User getCustomer() {
        return customer;
    }

    public void setCustomer(com.example.makeup_booking_app.models.User customer) {
        this.customer = customer;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public com.example.makeup_booking_app.models.Service getService() {
        return service;
    }

    public void setService(com.example.makeup_booking_app.models.Service service) {
        this.service = service;
    }

    public com.example.makeup_booking_app.models.Branch getBranch() {
        return branch;
    }

    public void setBranch(com.example.makeup_booking_app.models.Branch branch) {
        this.branch = branch;
    }

    public Instant getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Instant appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}