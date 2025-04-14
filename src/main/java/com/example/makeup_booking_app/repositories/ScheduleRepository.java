
package com.example.makeup_booking_app.repositories;

import com.example.makeup_booking_app.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByArtistId(Long artistId);
    List<Schedule> findByArtistIdAndWorkDate(Long artistId, LocalDate workDate);
}
