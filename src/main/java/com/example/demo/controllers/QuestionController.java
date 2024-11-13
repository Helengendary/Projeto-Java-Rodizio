package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.NewQuestionDto;
import com.example.demo.dto.Token;

@RestController
@RequestMapping("/question")
public class QuestionController {
    
    @PostMapping
    public ResponseEntity<String> post(@RequestAttribute("token") Token token, @RequestBody NewQuestionDto question){
        Long ownerId = token.getId();

        
    }
}
