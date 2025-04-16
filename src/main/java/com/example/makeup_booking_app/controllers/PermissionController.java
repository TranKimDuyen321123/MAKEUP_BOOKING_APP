package com.example.makeup_booking_app.controllers;

import com.example.makeup_booking_app.models.Permission;
import com.example.makeup_booking_app.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    // Lấy tất cả quyền
    @GetMapping
    public List<Permission> getAllPermissions() {
        return permissionService.findAll();
    }

    // Tạo mới quyền
    @PostMapping
    public Permission createPermission(@RequestBody Permission permission) {
        return permissionService.CreatePermission(permission);
    }

    // Cập nhật quyền (toàn phần)
    @PutMapping("/{id}")
    public Permission updatePermission(@PathVariable Long id, @RequestBody Permission permission) {
        permission.setId(id); // đảm bảo đúng ID khi update
        return permissionService.UpdatePermission(permission);
    }
}
