package com.example.makeup_booking_app.Controller;

import com.example.makeup_booking_app.models.Permission;
import com.example.makeup_booking_app.repositories.PermissionReporsitory;
import com.example.makeup_booking_app.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/per")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionReporsitory permissionReporsitory;

    @GetMapping("/permissions")
    public List<Permission> getAllPermission() {
        return permissionService.findAll();
    }

    @PostMapping("/create")
    public Long createPermission(@RequestBody Permission permission) {
        return permissionReporsitory.save(permission).getId();
    }

    @GetMapping("/{id}")
    public Permission getPermissionById(@PathVariable Long id) {
        return permissionReporsitory.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Permission updatePermission(@PathVariable Long id, @RequestBody Permission permission) {
        if (permissionReporsitory.existsById(id)) {
            permission.setId(id);
            return permissionReporsitory.save(permission);
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Permission patchPermission(@PathVariable Long id, @RequestBody Permission permission) {
        return (Permission) permissionService.partialUpdate(id, permission);
    }

    @DeleteMapping("/{id}")
    public void deletePermission(@PathVariable Long id) {
        permissionReporsitory.deleteById(id);
    }
}
