package com.example.makeup_booking_app.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

    private String username;
    private String password;
     public String  getUsername(){
         return this.username;
     }
     public String  getPassword(){
         return this.password;
     }

}