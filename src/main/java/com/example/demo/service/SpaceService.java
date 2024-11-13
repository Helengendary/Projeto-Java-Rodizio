package com.example.demo.service;

import com.example.demo.model.User;

public interface SpaceService {
    Boolean createSpace(String name, User owner);

    Boolean deleteSpace(Long idSpace);
    
    Boolean updateSpace(Long idSpace, String nome, User owner);
}
