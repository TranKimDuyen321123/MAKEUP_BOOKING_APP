package com.example.makeup_booking_app.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "working_hours")
public class WorkingHour {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek;

    @Column(name = "opening_time", nullable = false)
    private String openingTime;

    @Column(name = "closing_time", nullable = false)
    private String closingTime;

    // Constructors, Getters, Setters, and toString methods
    public WorkingHour() {
    }

    public WorkingHour(String id, Branch branch, String dayOfWeek, String openingTime, String closingTime) {
        this.id = id;
        this.branch = branch;
        this.dayOfWeek = dayOfWeek;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    @Override
    public String toString() {
        return "WorkingHour{" +
                "id='" + id + '\'' +
                ", branch=" + branch +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", openingTime='" + openingTime + '\'' +
                ", closingTime='" + closingTime + '\'' +
                '}';
    }
}
