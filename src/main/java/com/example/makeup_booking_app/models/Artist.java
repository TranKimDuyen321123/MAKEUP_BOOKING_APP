package com.example.makeup_booking_app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private com.example.makeup_booking_app.models.User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "branch_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private com.example.makeup_booking_app.models.Branch branch;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "experience")
    private Integer experience;

//    @Column(name = "status")
//    private String status = "AVAILABLE";
     @Column(name = "status", columnDefinition = "VARCHAR(50) DEFAULT 'AVAILABLE'")
     private String status = "AVAILABLE";


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.example.makeup_booking_app.models.User getUser() {
        return user;
    }

    public void setUser(com.example.makeup_booking_app.models.User user) {
        this.user = user;
    }

    public com.example.makeup_booking_app.models.Branch getBranch() {
        return branch;
    }

    public void setBranch(com.example.makeup_booking_app.models.Branch branch) {
        this.branch = branch;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

}