package com.example.makeup_booking_app.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reviews")
public class Review {

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

    @Column(name = "rating", nullable = false)
    private int rating;

    @Lob
    @Column(name = "comment")
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false,
            insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    public Review() {
    }

    public Review(Long id, User user, MakeupArtist makeupArtist, int rating, String comment) {
        this.id = id;
        this.user = user;
        this.makeupArtist = makeupArtist;
        this.rating = rating;
        this.comment = comment;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Review{" +
                "id=" + id +
                ", user=" + user +
                ", makeupArtist=" + makeupArtist +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public MakeupArtist getMakeupArtist() {return makeupArtist;}

    public void setMakeupArtist(MakeupArtist makeupArtist) {this.makeupArtist = makeupArtist;}

    public int getRating() {return rating;}

    public void setRating(int rating) {this.rating = rating;}

    public String getComment() {return comment;}

    public void setComment(String comment) {this.comment = comment;}

    public Timestamp getCreatedAt() {return createdAt;}

    public void setCreatedAt(Timestamp createdAt) {this.createdAt = createdAt;
    }

}
