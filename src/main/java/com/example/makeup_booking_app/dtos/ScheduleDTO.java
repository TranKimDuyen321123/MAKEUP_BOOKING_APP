package com.example.makeup_booking_app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScheduleDTO {
    private Long id;
    private Long artistId;
    private LocalDate workDate;
    private String shift;
    private String status;
}
