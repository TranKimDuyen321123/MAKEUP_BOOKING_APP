package com.example.makeup_booking_app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.makeup_booking_app.Entity.MakeupArtistProfile;

@Repository
public interface MakeupArtistProfileRepository extends JpaRepository<MakeupArtistProfile, Long> {
    List<MakeupArtistProfile> findByBranch_BranchId(Long branchId); // Sử dụng branch.branchId để truy cập
}