package com.example.makeup_booking_app.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "makeup_artist_id", nullable = false)
    private MakeupArtist makeupArtist;

    @Column(name = "appointment_time", nullable = false)
    private Timestamp appointmentTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.PENDING;

    @Column(name = "created_at", nullable = false, updatable = false,
            insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    public Appointment() {
    }

    public Appointment(Long id, User user, MakeupArtist makeupArtist, Timestamp appointmentTime, com.example.makeup_booking_app.model.Appointment.Status status) {
        this.id = id;
        this.user = user;
        this.makeupArtist = makeupArtist;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", user=" + user +
                ", makeupArtist=" + makeupArtist +
                ", appointmentTime=" + appointmentTime +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public MakeupArtist getMakeupArtist() {return makeupArtist;}

    public void setMakeupArtist(MakeupArtist makeupArtist) {this.makeupArtist = makeupArtist;}

    public Timestamp getAppointmentTime() {return appointmentTime;}

    public void setAppointmentTime(Timestamp appointmentTime) {this.appointmentTime = appointmentTime;}

    public Status getStatus() {return status;}

    public void setStatus(Status status) {this.status = status;}

    public Timestamp getCreatedAt() {return createdAt;}

    public void setCreatedAt(Timestamp createdAt) {this.createdAt = createdAt;
    }

    public enum Status {PENDING, CONFIRMED, CANCELLED}
}
