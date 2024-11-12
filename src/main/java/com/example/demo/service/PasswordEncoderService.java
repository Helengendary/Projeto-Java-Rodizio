package com.example.demo.service;

public interface PasswordEncoderService
{
    String encode(String password);
    boolean matches(String password, String encodedPassword);
}