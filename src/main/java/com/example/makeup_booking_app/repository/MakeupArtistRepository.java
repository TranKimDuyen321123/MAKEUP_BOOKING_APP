package com.example.makeup_booking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.makeup_booking_app.model.MakeupArtist;
import java.util.List;

@Repository
public interface MakeupArtistRepository extends JpaRepository<MakeupArtist, Long> {
    List<MakeupArtist> findByBranch_Id(Long branchId);

    List<MakeupArtist> findByUser_Id(Long userId);

    List<MakeupArtist> findBySpecialtyContaining(String keyword);
}
