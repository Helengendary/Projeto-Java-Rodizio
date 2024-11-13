package com.example.demo.service;

import com.example.demo.model.User;

public interface AuthService
{
    User login(String login, String password);
    String getAuth(User user);
}