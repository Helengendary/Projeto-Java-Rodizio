package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.UserRepository;

@CrossOrigin(origins = {"http://localhost:5257"})
@RestController
@RequestMapping("/user")
public class RegisterController {
    
    @Autowired UserRepository userRepo;

    @PostMapping()
    public ResponseEntity<String> postNewUser(@RequestBody NewUserDto user){
        
    }

}
