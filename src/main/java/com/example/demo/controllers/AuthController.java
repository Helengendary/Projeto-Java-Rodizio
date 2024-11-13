package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthDto;
import com.example.demo.dto.ReturnAuth;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping()
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<ReturnAuth> login(@RequestBody AuthDto user) {
        var login = authService.login(user.edv(), user.password());
        
        if(login == null)
            return new ResponseEntity<>(new ReturnAuth("null", "Usuário não encontrado"), HttpStatus.BAD_REQUEST);
        
        var token = authService.getAuth(login);

        return new ResponseEntity<>(new ReturnAuth(String.valueOf(token), "Usuário cadastrado com sucesso"), HttpStatus.OK);
    }

}
