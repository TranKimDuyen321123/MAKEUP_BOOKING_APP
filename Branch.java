package com.example.makeup_booking_app;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
@Table(name = "branches")
public class Branch {
    @Id
    @Column(name = "id", nullable = false, unique = true) // Ma chi nhanh (khoa chinh)
    private String id;

    @Column(name = "name", nullable = false) // Ten chi nhanh
    private String name;

    @Column(name = "address", nullable = false) // Dia chi chi nhanh
    private String address;

    @Column(name = "latitude", nullable = false) // Vi do GPS
    private double latitude;

    @Column(name = "longitude", nullable = false) // Kinh do GPS
    private double longitude;

    public Branch() {
    }

    public Branch(String id, String name, String address, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
