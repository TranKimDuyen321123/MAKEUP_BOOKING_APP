package com.example.makeup_booking_app.repositories;

import com.example.makeup_booking_app.models.Artist;
import com.example.makeup_booking_app.models.Branch;
import com.example.makeup_booking_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    // Tìm tất cả artist theo chi nhánh
    List<Artist> findByBranch(Branch branch);

    // Tìm artist theo specialty (chuyên môn)
    List<Artist> findBySpecialtyContainingIgnoreCase(String specialty);

    // Tìm artist theo số năm kinh nghiệm lớn hơn hoặc bằng
    List<Artist> findByExperienceGreaterThanEqual(Integer experience);

    // Tìm artist theo User (vì có OneToOne)
    Artist findByUser(User user);

    // Tìm artist theo ID User
    Artist findByUserId(Long userId);
    //tinh artist
    long countByStatus(String status);
    long countByStatusAndBranchId(String status, Long branchId);
}
