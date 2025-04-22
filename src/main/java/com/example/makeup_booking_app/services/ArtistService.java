package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.dtos.ArtistDTO;
import com.example.makeup_booking_app.models.Artist;
import com.example.makeup_booking_app.models.Branch;
import com.example.makeup_booking_app.models.User;
import com.example.makeup_booking_app.repositories.ArtistRepository;
import com.example.makeup_booking_app.repositories.BranchRepository;
import com.example.makeup_booking_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BranchRepository branchRepository;

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Artist getArtistById(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found with id: " + id));
    }

    public Artist createArtist(Artist artist) {
        User user = userRepository.findById(artist.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Branch branch = null;
        if (artist.getBranch() != null && artist.getBranch().getId() != null) {
            branch = (Branch) branchRepository.findById(artist.getBranch().getId()).orElse(null);
        }

        artist.setUser(user);
        artist.setBranch(branch);

        return artistRepository.save(artist);
    }

    public Artist updateArtist(Long id, Artist updatedArtist) {
        Artist existing = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        existing.setSpecialty(updatedArtist.getSpecialty());
        existing.setExperience(updatedArtist.getExperience());

        if (updatedArtist.getBranch() != null && updatedArtist.getBranch().getId() != null) {
            Branch branch = (Branch) branchRepository.findById(updatedArtist.getBranch().getId()).orElse(null);
            existing.setBranch(branch);
        }

        return artistRepository.save(existing);
    }

    public void deleteArtist(Long id) {
        if (!artistRepository.existsById(id)) {
            throw new RuntimeException("Artist not found");
        }
        artistRepository.deleteById(id);
    }

    public long countByStatus(String status) {
        return artistRepository.countByStatus(status);
    }

    public ArtistDTO toDTO(Artist artist) {
        ArtistDTO dto = new ArtistDTO();
        dto.setId(artist.getId());

        if (artist.getUser() != null) {
            dto.setUsername(artist.getUser().getUsername());
            dto.setEmail(artist.getUser().getEmail());
        }

        if (artist.getBranch() != null) {
            dto.setBranchId(artist.getBranch().getId());
            dto.setBranchName(artist.getBranch().getName());
        }

        dto.setSpecialty(artist.getSpecialty());
        dto.setExperience(artist.getExperience());
        dto.setStatus(artist.getStatus());

        return dto;
    }

}
