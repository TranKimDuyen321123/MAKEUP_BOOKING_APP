package com.example.makeup_booking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Import này
import org.springframework.data.repository.query.Param; // Import này
import java.util.List;  // Import này
import com.example.makeup_booking_app.AppointmentService;

public interface AppointmentServiceRepository extends JpaRepository<AppointmentService, String> {
    // Các phương thức khác
    @Query("SELECT ms.name, COUNT(aps.makeupService.id) FROM AppointmentService aps JOIN aps.makeupService ms WHERE aps.appointment.branch.id = :branchId GROUP BY ms.name")
    List<Object[]> countServicesByBranchId(@Param("branchId") String branchId);
}
