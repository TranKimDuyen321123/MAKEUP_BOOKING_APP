package com.example.makeup_booking_app.Controller;

import jakarta.persistence.EntityNotFoundException;
import com.example.makeup_booking_app.Entity.MakeupArtistProfile;
import com.example.makeup_booking_app.Services.MakeupArtistProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/makeup-artists")
//@RequiredArgsConstructor
public class MakeupArtistProfileController {
    private final MakeupArtistProfileService service;

    @Autowired
    public MakeupArtistProfileController(MakeupArtistProfileService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MakeupArtistProfile>> getAllMakeupArtists() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<MakeupArtistProfile>> getMakeupArtistsByBranch(@PathVariable Long branchId) {
        return ResponseEntity.ok(service.getByBranch_BranchId(branchId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MakeupArtistProfile> getMakeupArtistById(@PathVariable Long id) {
        Optional<MakeupArtistProfile> makeupArtist = service.getMakeupArtistById(id);
        return makeupArtist.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MakeupArtistProfile> createMakeupArtist(@Valid @RequestBody MakeupArtistProfile artist) {
        MakeupArtistProfile createdArtist = service.createMakeupArtist(artist);
        return new ResponseEntity<>(createdArtist, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MakeupArtistProfile> updateMakeupArtist(@PathVariable Long id, @Valid @RequestBody MakeupArtistProfile updatedArtist) {
        try {
            MakeupArtistProfile updated = service.updateMakeupArtist(id, updatedArtist);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMakeupArtist(@PathVariable Long id) {
        try {
            service.deleteMakeupArtist(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}