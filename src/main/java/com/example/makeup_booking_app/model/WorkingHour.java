package com.example.makeup_booking_app.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "working_hours")
public class WorkingHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "makeup_artist_id", nullable = false)
    private MakeupArtist makeupArtist;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "break_time")
    private LocalTime breakTime;

    // Constructors, Getters, Setters, and toString methods
    public WorkingHour() {
    }

    public WorkingHour(Long id, MakeupArtist makeupArtist, com.example.makeup_booking_app.model.WorkingHour.DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, LocalTime breakTime) {
        this.id = id;
        this.makeupArtist = makeupArtist;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.breakTime = breakTime;
    }

    @Override
    public String toString() {
        return "WorkingHour{" +
                "id=" + id +
                ", makeupArtist=" + makeupArtist +
                ", dayOfWeek=" + dayOfWeek +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", breakTime=" + breakTime +
                '}';
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public MakeupArtist getMakeupArtist() {return makeupArtist;}

    public void setMakeupArtist(MakeupArtist makeupArtist) {this.makeupArtist = makeupArtist;}

    public DayOfWeek getDayOfWeek() {return dayOfWeek;}

    public void setDayOfWeek(DayOfWeek dayOfWeek) {this.dayOfWeek = dayOfWeek;}

    public LocalTime getStartTime() {return startTime;}

    public void setStartTime(LocalTime startTime) {this.startTime = startTime;}

    public LocalTime getEndTime() {return endTime;}

    public void setEndTime(LocalTime endTime) {this.endTime = endTime;}

    public LocalTime getBreakTime() {return breakTime;}

    public void setBreakTime(LocalTime breakTime) {this.breakTime = breakTime;}

    public enum DayOfWeek {Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday}
}
