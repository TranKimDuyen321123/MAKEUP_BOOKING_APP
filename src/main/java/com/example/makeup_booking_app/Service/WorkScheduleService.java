package com.example.makeup_booking_app.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import com.example.makeup_booking_app.Entity.WorkSchedule; // Import entity
import com.example.makeup_booking_app.Repository.WorkScheduleRepository; // Import repository
import com.example.makeup_booking_app.Entity.MakeupArtistProfile; // Import entity
import com.example.makeup_booking_app.Repository.MakeupArtistProfileRepository; // Import repository
import jakarta.persistence.EntityNotFoundException;
@Service
//@RequiredArgsConstructor
public class WorkScheduleService {
    private final WorkScheduleRepository repository;
    private final MakeupArtistProfileRepository artistRepository;

    @Autowired
    public WorkScheduleService(WorkScheduleRepository repository, MakeupArtistProfileRepository artistRepository) {
        this.repository = repository;
        this.artistRepository = artistRepository;
    }

    public List<WorkSchedule> getAll() {
        return repository.findAll();
    }

    public WorkSchedule create(Long artistId, WorkSchedule schedule) {
        MakeupArtistProfile artist = artistRepository.findById(artistId).orElseThrow(() -> new EntityNotFoundException("Makeup Artist not found with ID: " + artistId));
        schedule.setArtist(artist);
        return repository.save(schedule);}

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Work Schedule not found with ID: " + id);}
        repository.deleteById(id);}
}
