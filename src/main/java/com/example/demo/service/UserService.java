package com.example.demo.service;

public interface UserService {
    Boolean createUser(String edv, String email, String pass);

    Boolean deleteUser(Long idUser);

    Boolean UpdatePass(String edv, String newPass);
}
