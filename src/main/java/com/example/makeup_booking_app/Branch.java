package com.example.makeup_booking_app;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    public Branch() {
    }

    public Branch(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Getters
    public Long getBranchId() {
        return branchId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setBranchId(Long branchId) { // Giữ nguyên setter
        this.branchId = branchId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
