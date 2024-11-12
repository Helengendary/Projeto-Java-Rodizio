package com.example.demo.service;

public interface JwtService<T>
{
    String get(T token);
    T validate(String jwt);
}