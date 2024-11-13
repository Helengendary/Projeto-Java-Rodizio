package com.example.demo.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.Spaces;
import com.example.demo.model.User;
import com.example.demo.repositories.SpacesRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.SpaceService;

public class SpaceImpl implements SpaceService {

    @Autowired 
    SpacesRepository spaceRepo;

    @Autowired
    UserRepository userRepo;

    @Override
    public Boolean createSpace(String name, User spaceOwner) {
        Spaces newSpace = new Spaces();

        newSpace.setName(name);
        newSpace.setOwner(spaceOwner);

        spaceRepo.save(newSpace);

        return true;
    }

    @Override
    public Boolean deleteSpace(Long idSpace) {
       Optional<Spaces> spaces = spaceRepo.findById(idSpace);

        if(!spaces.isPresent())
            return false;

        spaceRepo.delete(spaces.get());

        return true;
    }

    @Override
    public Boolean updateSpace(Long idSpace, String nome, User owner) {
      
        throw new UnsupportedOperationException("Unimplemented method 'updateSpace'");
    }
    
}
