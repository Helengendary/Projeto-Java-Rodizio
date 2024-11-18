package com.example.demo.service;

import com.example.demo.model.Spaces;
import com.example.demo.model.User;

public interface PermissionService {
    Boolean editPermission(Spaces space, User user, Boolean idOwnerOrAdm);
    Boolean createPermission(User user, Spaces space, Boolean adm);
    Boolean deletePermission(Long idPermission);
}
