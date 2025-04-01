package com.example.makeup_booking_app;

import com.example.makeup_booking_app.Branch;
import com.example.makeup_booking_app.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;

    //Tim kiem chi nhanh theo ten
    public List<Branch> findByName(String name){
        return branchRepository.findByName(name);
    }

    //Tim kiem chi nhanh theo ten chua tu khoa
    public List<Branch> findByNameContaining(String keyword){
        return branchRepository.findByNameContaining(keyword);
    }

    //Tim kiem chi nhanh theo dia chi
    public List<Branch> findByAddress(String address){
        return branchRepository.findByAddress(address);
    }

    //Tim kiem chi nhanh theo dia chi chua tu khoa
    public List<Branch> findByAddressContaining(String keyword){
        return branchRepository.findByAddressContaining(keyword);
    }

    //Tim kiem chi nhanh theo so dien thoai
    public List<Branch> findByPhone(String phone){
        return branchRepository.findByPhone(phone);
    }

    //Tim kiem chi nhanh theo thoi gian tao
    public List<Branch> findByCreatedAt(Timestamp createdAt){
        return branchRepository.findByCreatedAt(createdAt);
    }

    //Xoa chi nhanh theo ten
    public void deleteByName(String name){
        branchRepository.deleteByName(name);
    }

    //Xoa chi nhanh theo dia chi
    public void deleteByAddress(String address){
        branchRepository.deleteByAddress(address);
    }

    //Xoa chi nhanh theo so dien thoai
    public void deleteByPhone(String phone){
        branchRepository.deleteByPhone(phone);
    }

    //Xoa chi nhanh theo thoi gian tao
    public void deleteByCreatedAt(Timestamp createdAt){
        branchRepository.deleteByCreatedAt(createdAt);
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
