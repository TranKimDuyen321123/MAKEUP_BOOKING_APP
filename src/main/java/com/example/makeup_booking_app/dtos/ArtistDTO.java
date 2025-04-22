
package com.example.makeup_booking_app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtistDTO {
    private Long id;
    private String username; // lấy từ user
    private String email; // nếu cần
    private Long branchId;
    private String branchName;
    private String specialty;
    private Integer experience;
    private String status;

}
