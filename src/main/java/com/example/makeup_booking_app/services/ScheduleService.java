package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.dtos.ScheduleDTO;
import com.example.makeup_booking_app.models.Artist;
import com.example.makeup_booking_app.models.Schedule;
import com.example.makeup_booking_app.repositories.ArtistRepository;
import com.example.makeup_booking_app.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ArtistRepository artistRepository;

    // Lấy toàn bộ lịch
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Lấy lịch theo chuyên viên
    public List<ScheduleDTO> getSchedulesByArtist(Long artistId) {
        return scheduleRepository.findByArtistId(artistId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Lấy chi tiết một lịch
    public ScheduleDTO getScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        return convertToDTO(schedule);
    }

    //  Tạo lịch mới
    public ScheduleDTO createSchedule(ScheduleDTO dto) {
        Artist artist = artistRepository.findById(dto.getArtistId())
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        Schedule schedule = new Schedule();
        schedule.setArtist(artist);
        schedule.setShift(dto.getShift());
        schedule.setStatus(dto.getStatus());
        schedule.setWorkDate(dto.getWorkDate());

        return convertToDTO(scheduleRepository.save(schedule));
    }

    //  Cập nhật lịch
    public ScheduleDTO updateSchedule(Long id, ScheduleDTO dto) {
        Schedule existing = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        existing.setShift(dto.getShift());
        existing.setStatus(dto.getStatus());
        existing.setWorkDate(dto.getWorkDate());

        return convertToDTO(scheduleRepository.save(existing));
    }

    // Xoá lịch
    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new RuntimeException("Schedule not found");
        }
        scheduleRepository.deleteById(id);
    }

    // Chuyển từ Entity sang DTO
    private ScheduleDTO convertToDTO(Schedule schedule) {
        return new ScheduleDTO(
                schedule.getId(),
                schedule.getArtist().getId(),
                schedule.getWorkDate(),
                schedule.getShift(),
                schedule.getStatus()
        );
    }
}
