package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
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
import com.example.demo.repositories.SpacesRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.dto.Token;
import com.example.demo.model.Spaces;
import com.example.demo.model.User;

@RestController
@RequestMapping("/spaces")
public class SpaceController {
    
    @Autowired
    SpacesRepository spaceRepo;

    @Autowired 
    UserRepository userRepo;

    @GetMapping()
    public ResponseEntity<List<Spaces>> getUser(String query, Integer page, Integer size){
        if (query.isBlank())
            query = "";

        if (page == null)
            page = 1;

        if (size == null)
            size = 2;

        Pageable pageable = PageRequest.of(page,size);

        List<Spaces> spaces = spaceRepo.queryFindByName(query, pageable);
        
        return new ResponseEntity<>(spaces, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestAttribute("token") Token token, @RequestBody NewSpaceDto space){
        Long ownerId = token.getId();

        Spaces newSpace = new Spaces();

        Optional<User> ownerOptional = userRepo.findById(ownerId);

        if(!ownerOptional.isPresent())
            return new ResponseEntity<>("Owner not found", HttpStatus.NOT_FOUND);

        User spaceOwner = ownerOptional.get();

        newSpace.setName(space.name());
        newSpace.setId(ownerId);
        newSpace.setOwner(spaceOwner);

        spaceRepo.save(newSpace);

        return new ResponseEntity<>("Space created", HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSpace(@RequestAttribute ("token") Token token, @RequestBody DeleteSpaceDto space){
        Optional<Spaces> spaces = spaceRepo.findById(space.id());

        if (!spaces.isPresent()) 
            return new ResponseEntity<>("Space not found", HttpStatus.NOT_FOUND);
            
        spaceRepo.delete(spaces.get());

        return new ResponseEntity<>("Space deleted", HttpStatus.OK);
        

    }
}
