package com.example.makeup_booking_app.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "makeup_services")
public class MakeupService {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "duration", nullable = false)
    private int duration; // Duration in minutes

    // Constructors, Getters, Setters, and toString methods
    public MakeupService() {
    }

    public MakeupService(String id, String serviceName, double price, int duration) {
        this.id = id;
        this.serviceName = serviceName;
        this.price = price;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "MakeupService{" +
                "id='" + id + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}
