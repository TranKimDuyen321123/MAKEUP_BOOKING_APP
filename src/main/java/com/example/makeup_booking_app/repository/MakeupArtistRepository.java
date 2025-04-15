package com.example.makeup_booking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.makeup_booking_app.model.MakeupArtist;

@Repository
public interface MakeupArtistRepository extends JpaRepository<MakeupArtist, String> {
    List<MakeupArtist> findByBranch_BranchId(String branchId); // Sử dụng branch.branchId để truy cập
}