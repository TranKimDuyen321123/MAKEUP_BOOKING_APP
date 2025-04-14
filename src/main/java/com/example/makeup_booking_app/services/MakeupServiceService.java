package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.models.Booking;
import com.example.makeup_booking_app.models.MakeupService;
import com.example.makeup_booking_app.repositories.MakeupServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MakeupServiceService {

    @Autowired
    private MakeupServiceRepository makeupServiceRepository;

    public List<MakeupService> getAllServices() {
        return makeupServiceRepository.findAll();
    }

    public Optional<MakeupService> getServiceById(Long id) {
        return makeupServiceRepository.findById(id);
    }

    public MakeupService createService(MakeupService makeupService) {
        return makeupServiceRepository.save(makeupService);
    }

    public MakeupService updateService(Long id, MakeupService newService) {
        return makeupServiceRepository.findById(id)
                .map(service -> {
                    service.setName(newService.getName());
                    service.setDescription(newService.getDescription());
                    service.setPrice(newService.getPrice());
                    service.setDuration(newService.getDuration());
                    return makeupServiceRepository.save(service);
                })
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + id));
    }

    public void deleteService(Long id) {

    }

    public List<MakeupService> findByName(String name) {
        return List.of();
    }
}
