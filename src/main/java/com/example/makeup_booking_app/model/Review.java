package com.example.makeup_booking_app.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "review_text", nullable = false)
    private String reviewText;

    @Column(name = "rating", nullable = false)
    private double rating;

    @ManyToOne
    @JoinColumn(name = "makeup_service_id", nullable = false)
    private MakeupService makeupService;

    // Constructors, Getters, Setters, and toString methods
    public Review() {
    }

    public Review(String id, String reviewText, double rating, MakeupService makeupService) {
        this.id = id;
        this.reviewText = reviewText;
        this.rating = rating;
        this.makeupService = makeupService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public MakeupService getMakeupService() {
        return makeupService;
    }

    public void setMakeupService(MakeupService makeupService) {
        this.makeupService = makeupService;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", reviewText='" + reviewText + '\'' +
                ", rating=" + rating +
                ", makeupService=" + makeupService +
                '}';
    }
}
