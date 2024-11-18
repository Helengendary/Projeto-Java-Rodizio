package com.example.demo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dto.Token;
import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.JwtService;
import com.example.demo.service.PasswordEncoderService;

public class AuthImpl implements AuthService{
    @Autowired
    UserRepository repo;

    @Autowired
    private PasswordEncoderService passwordService;

    @Autowired
    JwtService<Token> jwtService;

    @Override
    public User login(String edv, String password) {
        List<User> user = repo.findByEdv(edv);
        
        if(user.isEmpty())
            return null;
        

        if(passwordService.matches(password, user.get(0).getPassword())){
            return user.get(0);
        }

        return null;
    }

    @Override
    public String getAuth(User user) {
        Token token = new Token();
        token.setId(user.getId());
        token.setEmail(user.getEmail());
        
        var jwt = jwtService.get(token);

        return String.valueOf(jwt);
    }
    
}
