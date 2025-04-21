package com.example.makeup_booking_app.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private String role;
    private String email;

}
