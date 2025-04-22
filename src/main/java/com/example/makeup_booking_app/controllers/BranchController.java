package com.example.makeup_booking_app.controllers;

import com.example.makeup_booking_app.dtos.BranchDTO;
import com.example.makeup_booking_app.models.Branch;
import com.example.makeup_booking_app.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping
    public ResponseEntity<List<BranchDTO>> getAllBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @PostMapping
    public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) {
        return ResponseEntity.ok(branchService.saveBranch(branch));
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<BranchDTO>> getByName(@RequestParam String name) {
        return ResponseEntity.ok(branchService.findByName(name));
    }

    @GetMapping("/search/name-contains")
    public ResponseEntity<List<BranchDTO>> getByNameContaining(@RequestParam String keyword) {
        return ResponseEntity.ok(branchService.findByNameContaining(keyword));
    }

    @GetMapping("/search/address")
    public ResponseEntity<List<BranchDTO>> getByAddress(@RequestParam String address) {
        return ResponseEntity.ok(branchService.findByAddress(address));
    }

    @GetMapping("/search/address-contains")
    public ResponseEntity<List<BranchDTO>> getByAddressContaining(@RequestParam String keyword) {
        return ResponseEntity.ok(branchService.findByAddressContaining(keyword));
    }

    @GetMapping("/search/phone")
    public ResponseEntity<List<BranchDTO>> getByPhone(@RequestParam String phone) {
        return ResponseEntity.ok(branchService.findByPhone(phone));
    }

    @GetMapping("/search/latitude")
    public ResponseEntity<List<BranchDTO>> getByLatitude(@RequestParam double latitude) {
        return ResponseEntity.ok(branchService.searchByLatitudeNear(latitude));
    }

    @GetMapping("/search/longitude")
    public ResponseEntity<List<BranchDTO>> getByLongitude(@RequestParam double longitude) {
        return ResponseEntity.ok(branchService.searchByLongitudeNear(longitude));
    }

    @GetMapping("/search/created-at")
    public ResponseEntity<List<BranchDTO>> getByCreatedAt(@RequestParam Instant createdAt) {
        return ResponseEntity.ok(branchService.findByCreatedAt(createdAt));
    }

    @DeleteMapping("/delete/name")
    public ResponseEntity<Void> deleteByName(@RequestParam String name) {
        branchService.deleteByName(name);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/address")
    public ResponseEntity<Void> deleteByAddress(@RequestParam String address) {
        branchService.deleteByAddress(address);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/phone")
    public ResponseEntity<Void> deleteByPhone(@RequestParam String phone) {
        branchService.deleteByPhone(phone);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/latitude")
    public ResponseEntity<Void> deleteByLatitude(@RequestParam double latitude) {
        branchService.deleteByLatitude(latitude);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/longitude")
    public ResponseEntity<Void> deleteByLongitude(@RequestParam double longitude) {
        branchService.deleteByLongitude(longitude);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/created-at")
    public ResponseEntity<Void> deleteByCreatedAt(@RequestParam Instant createdAt) {
        branchService.deleteByCreatedAt(createdAt);
        return ResponseEntity.noContent().build();
    }
}
