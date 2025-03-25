package com.example.makeup_booking_app;

import com.example.makeup_booking_app.Branch;
import com.example.makeup_booking_app.BranchRepository;
import com.example.makeup_booking_app.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/branches")
public class BranchController {
    @Autowired
    private BranchService branchService;

    //Tim chi nhanh theo ten
    @GetMapping("/search-by-name")
    public List<Branch> findByName(@RequestParam String name){
        return branchService.findByName(name);
    }

    //Tim chi nhanh theo ten chua tu khoa
    @GetMapping("/search-by-name-keyword")
    public List<Branch> findByNameContaining(@RequestParam String keyword){
        return branchService.findByNameContaining(keyword);
    }

    //Tim chi nhanh theo dia chi
    @GetMapping("/search-by-address")
    public List<Branch> findByAddress(@RequestParam String address){
        return branchService.findByAddress(address);
    }

    //Tim chi nhanh theo dia chi chua ky tu
    @GetMapping("/search-by-address-keyword")
    public List<Branch> findByAddressContaining(@RequestParam String keyword){
        return branchService.findByAddressContaining(keyword);
    }

    //Tim chi nhanh theo vi do
    @GetMapping("/search-by-latitude")
    public List<Branch> findByLatitude(@RequestParam double latitude){
        return branchService.findByLatitude(latitude);
    }

    //Tim chi nhanh theo kinh do
    @GetMapping("/search-by-longitude")
    public List<Branch> findByLongitude(@RequestParam double longitude){
        return branchService.findByLongitude(longitude);
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

    //Xoa chi nhanh theo vi do
    @GetMapping("/delete-by-latitude")
    public String deleteByLatitude(@RequestParam double latitude){
        branchService.deleteByLatitude(latitude);
        return "Branch '" + latitude + "' has been deleted.";
    }

    //Xoa chi nhanh theo kinh do
    @GetMapping("/delete-by-longitude")
    public String deleteByLongitude(@RequestParam double longitude){
        branchService.deleteByLongitude(longitude);
        return "Branch '" + longitude + "' has been deleted.";
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
