package com.example.makeup_booking_app;

import com.example.makeup_booking_app.Branch;
import com.example.makeup_booking_app.BranchRepository;
import com.example.makeup_booking_app.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchController {
    @Autowired
    private BranchService branchService;

    //Tim kiem chi nhanh theo ten
    @GetMapping("/search-by-name")
    public List<Branch> findByName(@RequestParam String name){
        return branchService.findByName(name);
    }

    //Tim kiem chi nhanh theo ten chua tu khoa
    @GetMapping("/search-by-name-keyword")
    public List<Branch> findByNameContaining(@RequestParam String keyword){
        return branchService.findByNameContaining(keyword);
    }

    //Tim kiem chi nhanh theo dia chi
    @GetMapping("/search-by-address")
    public List<Branch> findByAddress(@RequestParam String address){
        return branchService.findByAddress(address);
    }

    //Tim kiem chi nhanh theo dia chi chua ky tu
    @GetMapping("/search-by-address-keyword")
    public List<Branch> findByAddressContaining(@RequestParam String keyword){
        return branchService.findByAddressContaining(keyword);
    }

    //Tim kiem chi nhanh theo so dien thoai
    @GetMapping("/search-by-phone")
    public List<Branch> findByPhone(@RequestParam String phone){
        return branchService.findByPhone(phone);
    }

    //Tim kiem chi nhanh theo thoi gian tao
    @GetMapping("/search-by-created-at")
    public List<Branch> findByCreatedAt(@RequestParam Timestamp createdAt){
        return branchService.findByCreatedAt(createdAt);
    }

    //Xoa chi nhanh theo ten
    @DeleteMapping("/delete-by-name")
    public String deleteByName(@RequestParam String name){
        branchService.deleteByName(name);
        return "Branch '" + name + "' has been deleted.";
    }

    //Xoa chi nhanh theo dia chi
    @DeleteMapping("/delete-by-address")
    public String deleteByAddress(@RequestParam String address){
        branchService.deleteByAddress(address);
        return "Branch '" + address + "' has been deleted.";
    }

    //Xoa chi nhanh theo so dien thoai
    @DeleteMapping("/delete-by-phone")
    public String deleteByPhone(@RequestParam String phone){
        branchService.deleteByPhone(phone);
        return "Branch '" + phone + "' has been deleted.";
    }

    //Xoa chi nhanh theo thoi gian tao
    @DeleteMapping("/delete-by-created-at")
    public String deleteByCreatedAt(@RequestParam Timestamp createdAt){
        branchService.deleteByCreatedAt(createdAt);
        return "Branch '" + createdAt + "' has been deleted.";
    }

    //Them chi nhanh
    @PostMapping
    public Branch saveBranch(@RequestBody Branch branch){
        return branchService.saveBranch(branch);
    }

    //Lay danh sach tat ca cac chi nhanh
    @GetMapping
    public List<Branch> getAllBranches(){
        return branchService.getAllBranches();
    }
}
