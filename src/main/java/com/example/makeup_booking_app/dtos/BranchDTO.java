package com.example.makeup_booking_app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BranchDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Instant createdAt;
}