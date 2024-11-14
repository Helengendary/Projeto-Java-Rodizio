package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.NewQuestionDto;
import com.example.demo.dto.Token;
import com.example.demo.model.Permission;
import com.example.demo.model.User;
import com.example.demo.model.Question;
import com.example.demo.model.Spaces;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.SpacesRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.QuestionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionRepository repoQuestion;

    @Autowired
    QuestionService serviceQuestion;

    @Autowired
    SpacesRepository repoSpace;

    @Autowired
    PermissionRepository repoPerm;

    @Autowired
    UserRepository repoUser;

    @GetMapping("/{space}")
    public ResponseEntity<List<Question>> getQuestions(@PathVariable Long spaceId, Integer page, Integer size) {

        Spaces space = repoSpace.findById(spaceId).get();
        
        if(page == null)
            page = 1;
        
        if(size == null)
            size = 2;
        
        Pageable pageable = PageRequest.of(page, size);
        
        List<Question> questions = repoQuestion.findBySpace(space, pageable);

        if(questions.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestions(@PathVariable Long id) {

        Question question = repoQuestion.findById(id).get();

        if(question == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> postQuestion(@RequestAttribute("token") Token token, @RequestBody NewQuestionDto question) {

        User user = repoUser.findById(token.getId()).get();

        Spaces space = repoSpace.findById(question.idSpace()).get();

        List<Permission> permissions = repoPerm.findBySpaceAndParticipant(space, user);

        if(permissions.isEmpty())
            return new ResponseEntity<>("Voce não tem essa permissao", HttpStatus.FORBIDDEN);

        serviceQuestion.createQuestion(question.statement(), permissions.get(0));

        return new ResponseEntity<>("Pergunta enviada", HttpStatus.OK);
    }
    

}
