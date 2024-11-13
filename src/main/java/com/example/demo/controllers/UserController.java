package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.NewUserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.repositories.UserRepository;


@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired 
    UserService userService;

    @Autowired
    UserRepository repository;

    @PostMapping
    public ResponseEntity<String> postNewUser(@RequestBody NewUserDto user){

        if(user.email() == null || user.edv() == null || user.password() == null)
            return new ResponseEntity<>("Preencha todos os campos!", HttpStatus.BAD_REQUEST);
        
        userService.createUser(user.edv(), user.email(), user.password());

        return new ResponseEntity<>("User criado com sucesso!", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getMethodName(String query, Integer page, Integer size) {

        if(query.isBlank())
            query = "";
        
        if(page == null)
            page = 1;
        
        if(size == null)
            size = 10;

        Pageable pageable = PageRequest.of(page, size);

        List<User> users = repository.findByQuery(query, pageable);

        if(users.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
