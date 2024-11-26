package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PermissionDto;
import com.example.demo.dto.Token;
import com.example.demo.model.Permission;
import com.example.demo.model.Spaces;
import com.example.demo.model.User;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.SpacesRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.PermissionService;

@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    UserRepository userRepo;

    @Autowired  
    SpacesRepository spaceRepo;

    @Autowired
    PermissionRepository permissionRepo;

    @Autowired
    PermissionService permissionService;
    
    @PostMapping
    public ResponseEntity<String> postPermission(@RequestAttribute("token") Token token, @RequestBody PermissionDto permission){
        
        Optional<User> userAdm = userRepo.findById(token.getId());
        Optional<Spaces> space = spaceRepo.findById(permission.spaceId());

        List<Permission> permissions = permissionRepo.findBySpaceAndParticipant(space.get(), userAdm.get());

        if(permissions.isEmpty() || !permissions.get(0).getAdm())
            return new ResponseEntity<>("Voce não tem essa permissao", HttpStatus.FORBIDDEN);

        Optional<User> user = userRepo.findById(permission.userId());
        permissions = permissionRepo.findBySpaceAndParticipant(space.get(), user.get());

        if(permissions.isEmpty())
            if(permissionService.createPermission(user.get(), space.get(), permission.isAdm()))
                return new ResponseEntity<>("Permissao criada", HttpStatus.OK);
        
        if(!permissionService.editPermission(space.get(), user.get(), permissions.get(0).getAdm()))
            return new ResponseEntity<>("Algo deu errado", HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<>("Permissao alterada", HttpStatus.OK);
        
    }


    @GetMapping
    public List<Permission> get(Long spaceId){
        System.err.println(permissionRepo.findBySpaceId(spaceId).size());
        return permissionRepo.findBySpaceId(spaceId);
    }
}

