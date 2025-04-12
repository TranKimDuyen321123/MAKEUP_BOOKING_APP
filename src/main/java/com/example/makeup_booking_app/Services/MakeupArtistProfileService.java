package com.example.makeup_booking_app.Services;

import com.example.makeup_booking_app.Entity.MakeupArtistProfile;
import com.example.makeup_booking_app.Repository.MakeupArtistProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MakeupArtistProfileService {

    private final MakeupArtistProfileRepository repository;

    @Autowired
    public MakeupArtistProfileService(MakeupArtistProfileRepository repository) {
        this.repository = repository;
    }

    public List<MakeupArtistProfile> getAll() {
        return repository.findAll();
    }

    public List<MakeupArtistProfile> getByBranch_BranchId(Long branchId) {
        return repository.findByBranch_BranchId(branchId);
    }

    public Optional<MakeupArtistProfile> getMakeupArtistById(Long id) {
        return repository.findById(id);
    }

    public MakeupArtistProfile createMakeupArtist(MakeupArtistProfile artist) {
        return repository.save(artist);
    }

    public MakeupArtistProfile updateMakeupArtist(Long id, MakeupArtistProfile updatedArtist) {
        Optional<MakeupArtistProfile> existingArtistOptional = repository.findById(id);
        if (existingArtistOptional.isPresent()) {
            MakeupArtistProfile existingArtist = existingArtistOptional.get();

            existingArtist.setName(updatedArtist.getName());
            existingArtist.setPhone(updatedArtist.getPhone());
            existingArtist.setEmail(updatedArtist.getEmail());
            existingArtist.setSpecialty(updatedArtist.getSpecialty());
            existingArtist.setAvatar(updatedArtist.getAvatar());
            existingArtist.setBranch(updatedArtist.getBranch()); // Cần đảm bảo Branch tồn tại hoặc xử lý logic phù hợp
            return repository.save(existingArtist);
        } else {
            throw new EntityNotFoundException("Makeup Artist Profile not found with id: " + id);
        }
    }

    public void deleteMakeupArtist(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Makeup Artist Profile not found with id: " + id);
        }
        repository.deleteById(id);
    }
}