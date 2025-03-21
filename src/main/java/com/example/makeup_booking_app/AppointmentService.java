package com.example.makeup_booking_app;

import jakarta.persistence.*;

@Entity
@Table(name = "appointment_services")
public class AppointmentService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //TODO [Reverse Engineering] generate columns from DB
}