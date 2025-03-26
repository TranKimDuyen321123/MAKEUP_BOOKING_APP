package com.example.makeup_booking_app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.makeup_booking_app.MakeupService;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MakeupServiceRepository extends JpaRepository<MakeupService, Integer>{
    List<MakeupService> findByName(String name);

    List<MakeupService> findByNameContaining(String keyword);

    List<MakeupService> findByDescriptionContaining(String keyword);

    List<MakeupService> findByCategory(String category);

    List<MakeupService> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    List<MakeupService> findByDurationBetween(Integer minDuration, Integer maxDuration);

    void deleteByName(String name);

    void deleteByCategory(String category);

    boolean existsByName(String name);

    boolean existsByDescription(String description);

    boolean existsByCategory(String category);
}
