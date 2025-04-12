package com.example.makeup_booking_app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.makeup_booking_app.Entity.WorkSchedule; // Import entity
@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long> {}
