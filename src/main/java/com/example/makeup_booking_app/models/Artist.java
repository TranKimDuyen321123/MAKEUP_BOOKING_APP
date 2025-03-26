package com.example.makeup_booking_app.models;

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
    private com.example.makeup_booking_app.models.User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "branch_id")
    private com.example.makeup_booking_app.models.Branch branch;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "experience")
    private Integer experience;

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