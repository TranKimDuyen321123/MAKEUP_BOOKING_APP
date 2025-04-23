package com.example.makeup_booking_app.repository;

import com.example.makeup_booking_app.model.Appointment;
import com.example.makeup_booking_app.model.Appointment.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByUser_Id(Long userId);

    List<Appointment> findByMakeupArtist_Id(Long artistId);

    List<Appointment> findByStatus(Status status);

    List<Appointment> findByAppointmentTimeBetween(Timestamp start, Timestamp end);

    List<Appointment> findByMakeupArtist_IdAndStatus(Long artistId, Status status);
}
