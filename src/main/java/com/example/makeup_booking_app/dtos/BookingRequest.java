
package com.example.makeup_booking_app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingRequest {
    private Long customerId;
    private Long artistId;
    private Long serviceId;
    private Long branchId;
    private Instant appointmentTime;

}
