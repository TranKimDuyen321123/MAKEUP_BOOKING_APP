package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.dtos.BranchDTO;
import com.example.makeup_booking_app.models.Branch;
import com.example.makeup_booking_app.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;

    private BranchDTO toDTO(Branch branch) {
        return new BranchDTO(
                branch.getId(),
                branch.getName(),
                branch.getAddress(),
                branch.getPhone(),
                branch.getLatitude(),
                branch.getLongitude(),
                branch.getCreatedAt()
        );
    }

    public List<BranchDTO> findByName(String name){
        return branchRepository.findByName(name).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<BranchDTO> findByNameContaining(String keyword){
        return branchRepository.findByNameContaining(keyword).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<BranchDTO> findByAddress(String address){
        return branchRepository.findByAddress(address).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<BranchDTO> findByAddressContaining(String keyword){
        return branchRepository.findByAddressContaining(keyword).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<BranchDTO> findByPhone(String phone){
        return branchRepository.findByPhone(phone).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<BranchDTO> searchByLatitudeNear(double latitude) {
        return branchRepository.findByLatitudeNear(latitude).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<BranchDTO> searchByLongitudeNear(double longitude) {
        return branchRepository.findByLongitudeNear(longitude).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<BranchDTO> findByCreatedAt(Instant createdAt) {
        Timestamp timestamp = Timestamp.from(createdAt);
        return branchRepository.findByCreatedAt(timestamp)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    public void deleteByName(String name){
        branchRepository.deleteByName(name);
    }

    public void deleteByAddress(String address){
        branchRepository.deleteByAddress(address);
    }

    public void deleteByPhone(String phone){
        branchRepository.deleteByPhone(phone);
    }

    public void deleteByLatitude(double latitude) {
        branchRepository.deleteByLatitude(latitude);
    }

    public void deleteByLongitude(double longitude) {
        branchRepository.deleteByLongitude(longitude);
    }

    public void deleteByCreatedAt(Instant createdAt) {
        Timestamp timestamp = Timestamp.from(createdAt);
        branchRepository.deleteByCreatedAt(timestamp);
    }


    public Branch saveBranch(Branch branch){
        return branchRepository.save(branch);
    }

    public List<BranchDTO> getAllBranches(){
        return branchRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }
}
