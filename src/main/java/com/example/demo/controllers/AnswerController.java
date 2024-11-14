package com.example.demo.controllers;

import java.util.List;
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
import com.example.demo.model.Spaces;
import com.example.demo.model.User;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.SpacesRepository;
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

    @Autowired
    SpacesRepository spaceRepo;

    @PostMapping
    public ResponseEntity<String> post(@RequestAttribute("token") Token token,@RequestBody AnswerDto answer){
        
        Long ownerId = token.getId();

        Optional<User> answerOwner = userRepo.findById(ownerId);
        Optional<Question> question = questionRepo.findById(answer.questionId());
        Optional<Spaces> spaces = spaceRepo.findById(answer.spaceId());

        List<Permission> permissions = permissionRepo.findBySpaceAndParticipant(spaces.get(), answerOwner.get());

        if(permissions.isEmpty())
            return new ResponseEntity<>("Voce não tem essa permissao", HttpStatus.FORBIDDEN);


        if(!answerService.createAnswer(answer.statement(),answerOwner.get(), question.get()))
            return new ResponseEntity<>("A resposta não pode ser postada", HttpStatus.BAD_GATEWAY);
            
        return new ResponseEntity<>("Resposta criada", HttpStatus.CREATED);

        
    }
}
