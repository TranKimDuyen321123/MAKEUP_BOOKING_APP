package com.example.makeup_booking_app;

import com.example.makeup_booking_app.Branch;
import com.example.makeup_booking_app.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;

    //Tim chi nhanh theo ten
    public List<Branch> findByName(String name){
        return branchRepository.findByName(name);
    }

    //Tim chi nhanh theo ten chua tu khoa
    public List<Branch> findByNameContaining(String keyword){
        return branchRepository.findByNameContaining(keyword);
    }

    //Tim chi nhanh theo dia chi
    public List<Branch> findByAddress(String address){
        return branchRepository.findByAddress(address);
    }

    //Tim chi nhanh theo dia chi chua tu khoa
    public List<Branch> findByAddressContaining(String keyword){
        return branchRepository.findByAddressContaining(keyword);
    }

    //Tim chi nhanh theo vi do
    public List<Branch> findByLatitude(double latitude){
        return branchRepository.findByLongitude(latitude);
    }

    //Tim chi nhanh theo kinh do
    public List<Branch> findByLongitude(double longitude){
        return branchRepository.findByLatitude(longitude);
    }

    //Xoa chi nhanh theo ten
    public void deleteByName(String name){
        branchRepository.deleteByName(name);
    }

    //Xoa chi nhanh theo dia chi
    public void deleteByAddress(String address){
        branchRepository.deleteByAddress(address);
    }

    //Xoa chi nhanh theo vi do
    public void deleteByLatitude(double latitude){
        branchRepository.deleteByLatitude(latitude);
    }

    //Xoa chi nhanh theo kinh do
    public void deleteByLongitude(double longitude){
        branchRepository.deleteByLongitude(longitude);
    }

    //Them chi nhanh
    public Branch saveBranch(Branch branch){
        return branchRepository.save(branch);
    }

    //Lay danh sach tat ca cac chi nhanh
    public List<Branch> getAllBranches(){
        return branchRepository.findAll();
    }
}
