package com.example.makeup_booking_app.controllers;

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

    @GetMapping
    public List<MakeupService> getAllServices() {
        return makeupServiceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MakeupService> getServiceById(@PathVariable Long id) {
        return makeupServiceService.getServiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MakeupService createService(@RequestBody MakeupService makeupService) {
        return makeupServiceService.createService(makeupService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MakeupService> updateService(@PathVariable Long id, @RequestBody MakeupService newService) {
        try {
            MakeupService updated = makeupServiceService.updateService(id, newService);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        makeupServiceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
