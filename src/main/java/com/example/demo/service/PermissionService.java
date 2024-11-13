package com.example.demo.service;

import com.example.demo.model.Spaces;
import com.example.demo.model.User;

public interface PermissionService {
    Boolean createPermission(Spaces space, User user, Boolean idOwnerOrAdm);

    Boolean deletePermission(Spaces space, User user, Boolean idOwnerOrAdm);
}
