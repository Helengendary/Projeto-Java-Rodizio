package com.example.demo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.PasswordEncoderService;
import com.example.demo.service.UserService;

public class UserImpl implements UserService {

    @Autowired
    PasswordEncoderService passService;

    @Autowired
    UserRepository userRepository;

    @Override
    public Boolean createUser(String edv, String email, String pass) {
        String encryptedPassword = passService.encode(pass);

        User user = new User();
        user.setEdv(edv);
        user.setEmail(email);
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        return true;
    }

    @Override
    public Boolean deleteUser(Long idUser) {

        User user = userRepository.findById(idUser).get();
        
        if(user == null)
            return false;
        
        userRepository.delete(user);
        return true;
    }
    
    @Override
    public Boolean updatePass(String edv, String newPass) {
        
        List<User> user = userRepository.findByEdv(edv);

        if(user == null || user.size() <= 0)
            return false;

        user.get(0).setPassword(newPass);

        return true;
    }
    
}
