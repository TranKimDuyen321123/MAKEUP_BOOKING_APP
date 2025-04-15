package com.example.makeup_booking_app.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "appointment_time", nullable = false)
    private Timestamp appointmentTime;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "makeup_service_id", nullable = false)
    private MakeupService makeupService;

    // Constructors, Getters, Setters, and toString methods
    public Appointment() {
    }

    public Appointment(String id, String customerName, Timestamp appointmentTime, Branch branch, MakeupService makeupService) {
        this.id = id;
        this.customerName = customerName;
        this.appointmentTime = appointmentTime;
        this.branch = branch;
        this.makeupService = makeupService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Timestamp getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Timestamp appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public MakeupService getMakeupService() {
        return makeupService;
    }

    public void setMakeupService(MakeupService makeupService) {
        this.makeupService = makeupService
