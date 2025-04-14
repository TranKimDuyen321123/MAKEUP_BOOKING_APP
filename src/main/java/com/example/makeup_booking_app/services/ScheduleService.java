
package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.models.Artist;
import com.example.makeup_booking_app.models.Schedule;
import com.example.makeup_booking_app.repositories.ArtistRepository;
import com.example.makeup_booking_app.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ArtistRepository artistRepository;

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesByArtist(Long artistId) {
        return scheduleRepository.findByArtistId(artistId);
    }

    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    public Schedule createSchedule(Schedule schedule) {
        Artist artist = artistRepository.findById(schedule.getArtist().getId())
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        schedule.setArtist(artist);
        return scheduleRepository.save(schedule);
    }

    public Schedule updateSchedule(Long id, Schedule newSchedule) {
        Schedule existing = getScheduleById(id);

        existing.setWorkDate(newSchedule.getWorkDate());
        existing.setShift(newSchedule.getShift());
        existing.setStatus(newSchedule.getStatus());

        return scheduleRepository.save(existing);
    }

    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new RuntimeException("Schedule not found");
        }
        scheduleRepository.deleteById(id);
    }
}
