package com.example.makeup_booking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.makeup_booking_app.model.Branch;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.sql.Timestamp;

@Repository
public interface BranchRepository extends JpaRepository<Branch, String>{
    List<Branch> findByName(String name);

    List<Branch> findByNameContaining(String keyword);

    List<Branch> findByAddress(String address);

    List<Branch> findByAddressContaining(String keyword);

    List<Branch> findByPhone(String phone);

    List<Branch> findByLatitude(double latitude);

    List<Branch> findByLongitude(double longitude);

    List<Branch> findByCreatedAt(Timestamp createdAt);

    void deleteByName(String name);

    void deleteByAddress(String address);

    void deleteByPhone(String phone);

    void deleteByLatitude(double latitude);

    void deleteByLongitude(double longitude);

    void deleteByCreatedAt(Timestamp createdAt);
}