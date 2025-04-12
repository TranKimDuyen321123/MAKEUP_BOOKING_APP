package com.example.makeup_booking_app.Entity;

import com.example.makeup_booking_app.Branch;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "makeup_artist_profiles")
public class MakeupArtistProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "phone", unique = true, nullable = false)
    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    private String phone;

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "avatar")
    private String avatar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getAvatar() { return avatar; }
    public void setAvatar(String avatar) { this.avatar = avatar; }

    public Branch getBranch() { return branch; }
    public void setBranch(Branch branch) { this.branch = branch; }
}
