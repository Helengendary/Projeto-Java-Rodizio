package com.example.demo.service;

import com.example.demo.util.PasswordValidateCode;

public interface PasswordValidationService
{
    PasswordValidateCode validate(String password);
}