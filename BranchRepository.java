package com.example.makeup_booking_app;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.makeup_booking_app.Branch;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, String>{
    List<Branch> findByName(String name);

    List<Branch> findByNameContaining(String keyword);

    List<Branch> findByAddress(String address);

    List<Branch> findByAddressContaining(String keyword);

    List<Branch> findByLatitude(double latitude);

    List<Branch> findByLongitude(double longitude);

    void deleteByName(String name);

    void deleteByAddress(String address);

    void deleteByLatitude(double latitude);

    void deleteByLongitude(double longitude);
}
