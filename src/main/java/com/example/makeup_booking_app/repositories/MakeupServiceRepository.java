package com.example.makeup_booking_app.repositories;

import com.example.makeup_booking_app.models.MakeupService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.makeup_booking_app.services.MakeupServiceService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface MakeupServiceRepository extends JpaRepository<MakeupService, Long>{
    List<MakeupService> findByName(String name);
}
