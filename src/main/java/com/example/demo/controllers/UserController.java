package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.NewUserDto;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired 
    UserService userService;

    @Autowired
    UserRepository userRepo;

    @PostMapping
    public ResponseEntity<String> postNewUser(@RequestBody NewUserDto user){

        if(user.email() == null || user.edv() == null || user.password() == null)
            return new ResponseEntity<>("Preencha todos os campos!", HttpStatus.BAD_REQUEST);

        List<User> findedUsers = userRepo.findByEdv(user.edv());
        
        if(!findedUsers.isEmpty())
            return new ResponseEntity<>("Edv já cadastrado.", HttpStatus.FORBIDDEN);
        
        userService.createUser(user.edv(), user.email(), user.password());

        return new ResponseEntity<>("User criado com sucesso!", HttpStatus.OK);

        // {
        //     "edv":"1234567",
        //     "email":"don@don",
        //     "password":"1234"
        // }
    }

    @GetMapping
    public ResponseEntity<List<User>> getusers(String query, Integer page, Integer size) {

        if(query == null)
            query = "";
        
        if(page == null)
            page = 1;

        if(size == null)
            size = 10;
        
        List<User> users = userRepo.findByQuery(query, (page-1) * size, size);
        
        if(users.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);

        // user?query=a&page=2&size=3
    }

}
