package com.example.makeup_booking_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/services")
public class MakeupServiceController {
    @Autowired
    private MakeupServiceService makeupServiceService;

    //Tim kiem dich vu theo ten
    @GetMapping("/search-by-name")
    public List<MakeupService> findByName(@RequestParam String name){
        return makeupServiceService.findByName(name);
    }

    //Tim kiem dich vu theo ten chua tu khoa
    @GetMapping("/search-by-name-keyword")
    public List<MakeupService> findByNameContaining(@RequestParam String keyword){
        return makeupServiceService.findByNameContaining(keyword);
    }

    //Tim kiem dich vu theo mo ta chua tu khoa
    @GetMapping("/search-by-description-keyword")
    public List<MakeupService> findByDescriptionContaining(@RequestParam String keyword){
        return makeupServiceService.findByDescriptionContaining(keyword);
    }

    //Tim kiem dich vu theo loai
    @GetMapping("/search-by-category")
    public List<MakeupService> findByCategory(@RequestParam MakeupService.Category category){
        return makeupServiceService.findByCategory(category);
    }

    //Tim kiem dich vu theo khoang gia
    @GetMapping("/search-by-price")
    public List<MakeupService> findByPriceBetween(@RequestParam BigDecimal minPrice, BigDecimal maxPrice){
        return makeupServiceService.findByPriceBetween(minPrice, maxPrice);
    }

    //Tim kiem dich vu theo khoang thoi gian
    @GetMapping("/search-by-duration")
    public List<MakeupService> findByDurationBetween(@RequestParam Integer minDuration, Integer maxDuration){
        return makeupServiceService.findByDurationBetween(minDuration, maxDuration);
    }

    //Xoa dich vu theo ten
    @DeleteMapping("/delete-by-name")
    public String deleteByName(@RequestParam String name){
        makeupServiceService.deleteByName(name);
        return "Makeup Service '" + name + "' has been deleted.";
    }

    //Xoa dich vu theo loai
    @DeleteMapping("/delete-by-category")
    public String deleteByCategory(@RequestParam MakeupService.Category category){
        makeupServiceService.deleteByCategory(category);
        return "Makeup Service '" + category + "' has been deleted.";
    }

    //Kiem tra dich vu co ton tai theo ten
    @GetMapping("/exists-name")
    public boolean existsByName(@RequestParam String name){
        return makeupServiceService.existsByName(name);
    }

    //Kiem tra dich vu co ton tai theo mo ta
    @GetMapping("/exists-description")
    public boolean existsByDescription(@RequestParam String description){
        return makeupServiceService.existsByDescription(description);
    }

    //Kiem tra dich vu co ton tai theo loai
    @GetMapping("/exists-category")
    public boolean existsByCategory(@RequestParam MakeupService.Category category){
        return makeupServiceService.existsByCategory(category);
    }
}