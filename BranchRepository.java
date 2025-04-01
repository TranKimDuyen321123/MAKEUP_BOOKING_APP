package com.example.makeup_booking_app;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.makeup_booking_app.Branch;
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

    List<Branch> findByCreatedAt(Timestamp createdAt);

    void deleteByName(String name);

    void deleteByAddress(String address);

    void deleteByPhone(String phone);

    void deleteByCreatedAt(Timestamp createdAt);
}
