package com.example.demo.impl;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.service.PasswordEncoderService;

public class PasswordEncoderImpl implements PasswordEncoderService {
    private PasswordEncoder passwordEncoder;

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);  
    }

    @Override
    public boolean matches(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }
}
