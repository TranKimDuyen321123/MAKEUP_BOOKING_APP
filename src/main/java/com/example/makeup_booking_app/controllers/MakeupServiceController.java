package com.example.makeup_booking_app.controllers;

import com.example.makeup_booking_app.dtos.MakeupServiceDTO;
import com.example.makeup_booking_app.models.MakeupService;
import com.example.makeup_booking_app.services.MakeupServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class MakeupServiceController {

    @Autowired
    private MakeupServiceService makeupServiceService;

    // GET: /api/services/dtos → cho người dùng chọn khi đặt lịch
    @GetMapping("/dtos")
    public ResponseEntity<List<MakeupServiceDTO>> getAllServiceDTOs() {
        return ResponseEntity.ok(makeupServiceService.getAllServiceDTOs());
    }

    // GET: /api/services → trả toàn bộ entity (admin dùng)
    @GetMapping
    public ResponseEntity<List<MakeupService>> getAllServices() {
        return ResponseEntity.ok(makeupServiceService.getAllServices());
    }

    // GET: /api/services/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MakeupService> getServiceById(@PathVariable Long id) {
        return makeupServiceService.getServiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: /api/services → tạo mới (admin)
    @PostMapping
    public ResponseEntity<MakeupService> createService(@RequestBody MakeupService makeupService) {
        return ResponseEntity.ok(makeupServiceService.createService(makeupService));
    }

    // PUT: /api/services/{id} → cập nhật (admin)
    @PutMapping("/{id}")
    public ResponseEntity<MakeupService> updateService(
            @PathVariable Long id,
            @RequestBody MakeupService updatedService
    ) {
        return ResponseEntity.ok(makeupServiceService.updateService(id, updatedService));
    }

    // DELETE: /api/services/{id} → xoá (admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        makeupServiceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    // GET: /api/services/search?name=... → tìm kiếm theo tên
    @GetMapping("/search")
    public ResponseEntity<List<MakeupService>> searchServices(@RequestParam String name) {
        return ResponseEntity.ok(makeupServiceService.findByName(name));
    }
}
