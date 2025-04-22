package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.dtos.MakeupServiceDTO;
import com.example.makeup_booking_app.models.MakeupService;
import com.example.makeup_booking_app.repositories.MakeupServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MakeupServiceService {

    @Autowired
    private MakeupServiceRepository makeupServiceRepository;

    // Trả về danh sách dịch vụ (entity)
    public List<MakeupService> getAllServices() {
        return makeupServiceRepository.findAll();
    }

    // Trả về danh sách dịch vụ dưới dạng DTO cho client
    public List<MakeupServiceDTO> getAllServiceDTOs() {
        return makeupServiceRepository.findAll().stream()
                .map(service -> new MakeupServiceDTO(
                        service.getId(),
                        service.getName(),
                        service.getDescription(),
                        service.getPrice(),
                        service.getDuration()
                ))
                .collect(Collectors.toList());
    }

    // Tìm theo ID
    public Optional<MakeupService> getServiceById(Long id) {
        return makeupServiceRepository.findById(id);
    }

    // Tạo mới dịch vụ
    public MakeupService createService(MakeupService makeupService) {
        return makeupServiceRepository.save(makeupService);
    }

    // Cập nhật dịch vụ
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

    // Xoá dịch vụ
    public void deleteService(Long id) {
        if (!makeupServiceRepository.existsById(id)) {
            throw new RuntimeException("Service not found with id: " + id);
        }
        makeupServiceRepository.deleteById(id);
    }

    // Tìm kiếm dịch vụ theo tên (gợi ý khi người dùng nhập)
    public List<MakeupService> findByName(String name) {
        return makeupServiceRepository.findByNameContainingIgnoreCase(name);
    }
}
