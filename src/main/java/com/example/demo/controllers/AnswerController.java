package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AnswerDto;
import com.example.demo.dto.Token;
import com.example.demo.model.Permission;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.AnswerService;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    PermissionRepository permissionRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    AnswerService answerService;
    
    @Autowired
    QuestionRepository questionRepo;

    @PostMapping
    public ResponseEntity<String> post(@RequestAttribute("token") Token token,@RequestBody AnswerDto answer){
        
        Long ownerId = token.getId();

        Optional<User> answerOwner = userRepo.findById(ownerId);
        Optional<Question> question = questionRepo.findById(answer.questionId());
        



        if(!answerService.createAnswer(answer.statement(),answerOwner.get(), question.get()))
            return new ResponseEntity<>("A resposta não pode ser postada", HttpStatus.BAD_GATEWAY);
        return new ResponseEntity<>("Resposta criada", HttpStatus.CREATED);

        
    }
}
