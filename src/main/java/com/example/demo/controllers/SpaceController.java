package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DeleteSpaceDto;
import com.example.demo.dto.NewSpaceDto;
import com.example.demo.dto.SpaceQueryDto;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.SpacesRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.PermissionService;
import com.example.demo.service.SpaceService;
import com.example.demo.dto.Token;
import com.example.demo.model.Permission;
import com.example.demo.model.Spaces;
import com.example.demo.model.User;

@RestController
@RequestMapping("/spaces")
public class SpaceController {
    
    @Autowired
    SpacesRepository spaceRepo;

    @Autowired 
    UserRepository userRepo;

    @Autowired
    SpaceService spaceService;

    @Autowired
    PermissionRepository permissionRepo;

    @Autowired
    PermissionService permissionService;

    @GetMapping()
    public ResponseEntity<List<SpaceQueryDto>> get(String query, Integer page, Integer size){
        if (query == null)
            query = "";

        if (page == null)
            page = 1;

        if (size == null)
            size = 2;

        List<Spaces> spaces = spaceRepo.queryFindByName(query, (page-1)*size, size);

        List<SpaceQueryDto> spacesDto = new ArrayList<>();

        for (Spaces space : spaces) {
            SpaceQueryDto newSpace = new SpaceQueryDto(space.getId(), space.getName());
            spacesDto.add(newSpace);   
        }
        
        return new ResponseEntity<>(spacesDto, HttpStatus.ACCEPTED);
    }
    
    
    @PostMapping
    public ResponseEntity<String> post(@RequestAttribute("token") Token token, @RequestBody NewSpaceDto space){
        Long ownerId = token.getId();
        Optional<User> spaceOwner = userRepo.findById(ownerId);
        List<Spaces> findedSpaces = spaceRepo.findByName(space.name());
        
        if(!findedSpaces.isEmpty())
            return new ResponseEntity<>("Já existe um espaço com esse nome", HttpStatus.FORBIDDEN);
        
        spaceService.createSpace(space.name(), spaceOwner.get());
        findedSpaces = spaceRepo.findByName(space.name());

        permissionService.createPermission(spaceOwner.get(), findedSpaces.get(0), true);
        return new ResponseEntity<>("Espaço criado", HttpStatus.CREATED);
        // {
        //     "name":"espacoMaker"
        // }
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestAttribute ("token") Token token, @RequestBody DeleteSpaceDto space){

        Optional<Spaces> spaces = spaceRepo.findById(space.id());
        Optional<User> users = userRepo.findById(token.getId());

        if(spaces.isEmpty())
            return new ResponseEntity<>("Espaço não encontrado", HttpStatus.NOT_FOUND);

        List<Permission> permissions = permissionRepo.findBySpaceAndParticipant(spaces.get(), users.get());

        if (permissions.isEmpty() || !permissions.get(0).getAdm()) 
            return new ResponseEntity<>("Você não tem permissão", HttpStatus.FORBIDDEN);

        permissionService.deletePermission(permissions.get(0).getId());
            
        if(!spaceService.deleteSpace(space.id()))
            return new ResponseEntity<>("Erro deletando o espaço", HttpStatus.NOT_ACCEPTABLE);
            
        return new ResponseEntity<>("Espaço deletado", HttpStatus.OK);
    }
}
