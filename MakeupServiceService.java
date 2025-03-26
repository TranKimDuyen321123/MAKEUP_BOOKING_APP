package com.example.makeup_booking_app;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.makeup_booking_app.MakeupService;
import com.example.makeup_booking_app.MakeupServiceRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class MakeupServiceService {
    @Autowired
    private MakeupServiceRepository serviceRepository;

    //Tim dich vu theo ten
    public List<MakeupService> findByName(String name){
        return serviceRepository.findByName(name);
    }

    //Tim dich vu theo ten chua tu khoa
    public List<MakeupService> findByNameContaining(String keyword){
        return serviceRepository.findByNameContaining(keyword);
    }

    //Tim dich vu theo mo ta chua tu khoa
    public List<MakeupService> findByDescriptionContaining(String keyword){
        return serviceRepository.findByDescriptionContaining(keyword);
    }

    //Tim dich vu theo loai
    public List<MakeupService> findByCategory(String category){
        return serviceRepository.findByCategory(category);
    }

    //Tim dich vu theo khoang gia
    public List<MakeupService> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice){
        return serviceRepository.findByPriceBetween(minPrice, maxPrice);
    }

    //Tim dich vu theo khoang thoi gian
    public List<MakeupService> findByDurationBetween(Integer minDuration, Integer maxDuration){
        return serviceRepository.findByDurationBetween(minDuration, maxDuration);
    }

    //Xoa dich vu theo ten
    public void deleteByName(String name){
        serviceRepository.deleteByName(name);
    }

    //Xoa dich vu theo loai
    public void deleteByCategory(String category){
        serviceRepository.deleteByCategory(category);
    }

    //Kiem tra dich vu co ton tai theo ten
    public boolean existsByName(String name){
        return serviceRepository.existsByName(name);
    }

    //Kiem tra dich vu co ton tai theo mo ta
    public boolean existsByDescription(String description){
        return serviceRepository.existsByDescription(description);
    }

    //Kiem tra dich vu co ton tai theo loai
    public boolean existsByCategory(String category){
        return serviceRepository.existsByCategory(category);
    }
}
