package com.example.makeup_booking_app.repository;

import com.example.makeup_booking_app.model.AppointmentService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentServiceRepository extends JpaRepository<AppointmentService, Long> {

    List<AppointmentService> findByAppointment_Id(Long appointmentId);

    List<AppointmentService> findByMakeupService_Id(Long makeupServiceId);
}
