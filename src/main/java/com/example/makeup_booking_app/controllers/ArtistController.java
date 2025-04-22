package com.example.makeup_booking_app.controllers;

import com.example.makeup_booking_app.dtos.ArtistDTO;
import com.example.makeup_booking_app.models.Artist;
import com.example.makeup_booking_app.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    // Lấy tất cả chuyên viên (GET /api/artists)
    @GetMapping
    public List<ArtistDTO> getAllArtists() {
        return artistService.getAllArtists()
                .stream()
                .map(artistService::toDTO)
                .collect(Collectors.toList());
    }

    // Lấy chuyên viên theo ID (GET /api/artists/{id})
    @GetMapping("/{id}")
    public ArtistDTO getArtistById(@PathVariable Long id) {
        Artist artist = artistService.getArtistById(id);
        return artistService.toDTO(artist);
    }

    // Tạo mới chuyên viên (POST /api/artists)
    @PostMapping
    public Artist createArtist(@RequestBody Artist artist) {
        return artistService.createArtist(artist);
    }

    // Cập nhật chuyên viên (PUT /api/artists/{id})
    @PutMapping("/{id}")
    public Artist updateArtist(@PathVariable Long id, @RequestBody Artist artist) {
        return artistService.updateArtist(id, artist);
    }

    // Xoá chuyên viên (DELETE /api/artists/{id})
    @DeleteMapping("/{id}")
    public void deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
    }

    // Đếm số chuyên viên theo status (GET /api/artists/count?status=AVAILABLE)
    @GetMapping("/count")
    public long countByStatus(@RequestParam String status) {
        return artistService.countByStatus(status);
    }
}
