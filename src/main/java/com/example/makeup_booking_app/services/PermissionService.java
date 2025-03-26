package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.repositories.PermissionReporsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.makeup_booking_app.models.Permission;
import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionReporsitory permissionReporsitory;
    public List<Permission> findAll() {
        return permissionReporsitory.findAll();
    }

    public List<Permission> partialUpdate(Long id, Permission permission) {
        return permissionReporsitory.saveAll();
    }
}
