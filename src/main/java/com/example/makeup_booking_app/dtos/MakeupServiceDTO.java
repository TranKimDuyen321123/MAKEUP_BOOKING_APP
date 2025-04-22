package com.example.makeup_booking_app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MakeupServiceDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer duration;

}


