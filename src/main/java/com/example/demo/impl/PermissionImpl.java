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
    public Boolean editPermission(Spaces space, User user, Boolean idOwnerOrAdm) {
        
        List<Permission> permissions = permissionRepo.findBySpaceAndParticipant(space, user);

        if (permissions.isEmpty() || permissions.size() > 1)
            return false;
        
        permissions.get(0).setAdm(!idOwnerOrAdm);
            
        return true;
    }

    @Override
    public Boolean deletePermission(Long idPermission) {
        Permission permission = permissionRepo.findById(idPermission).get();

        if(permission == null)
            return false;
        permissionRepo.delete(permission);
        return true;
    }

    @Override
    public Boolean createPermission(User user, Spaces space, Boolean adm) {
        Permission newPermission = new Permission();
        newPermission.setParticipant(user);
        newPermission.setSpace(space);
        newPermission.setAdm(adm);

        permissionRepo.save(newPermission);
        return true;
    }
    
}
