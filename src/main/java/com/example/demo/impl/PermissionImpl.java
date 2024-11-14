package com.example.demo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Permission;
import com.example.demo.model.Spaces;
import com.example.demo.model.User;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.service.PermissionService;

public class PermissionImpl implements PermissionService {

    @Autowired
    PermissionRepository permissionRepo;

    @Override
    public Boolean createPermission(Spaces space, User user, Boolean idOwnerOrAdm) {
        
        List<Permission> permissions = permissionRepo.findBySpaceAndUser(space, user);

        if (permissions.isEmpty() || permissions.size() > 1)
            return false;
        
        permissions.get(0).setAdm(!idOwnerOrAdm);
            
        return true;
    }

    @Override
    public Boolean deletePermission(Spaces space, User user, Boolean idOwnerOrAdm) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePermission'");
    }
    
}
