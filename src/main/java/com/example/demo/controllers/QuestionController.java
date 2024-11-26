package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.NewQuestionDto;
import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.Token;
import com.example.demo.model.Permission;
import com.example.demo.model.Question;
import com.example.demo.model.Spaces;
import com.example.demo.model.User;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.SpacesRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionRepository questionRepo;

    @Autowired
    QuestionService questionService;

    @Autowired
    SpacesRepository spaceRepo;

    @Autowired
    PermissionRepository permissionRepo;

    @Autowired
    UserRepository userRepo;

    @GetMapping("/space/{spaceId}")
    public ResponseEntity<List<Question>> getQuestions(@PathVariable Long spaceId, Integer page, Integer size) {
        
        if(page == null)
            page = 1;
        
        if(size == null)
            size = 2;
        
        List<Question> questions = questionRepo.findBySpace(spaceId, (page-1)*size, size);

        if(questions.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        // List<QuestionDto> questionsDto = new ArrayList<>();

        // for (Question question : questions) {
        //     QuestionDto questionDto = new QuestionDto(question.getStatement(), question.getId() , question.getQuestioner().getId());
        //     questionsDto.add(questionDto);
        // }

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestions(@PathVariable Long id) {

        Question question = questionRepo.findById(id).get();
        
        if(question == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        QuestionDto questionDto = new QuestionDto(question.getStatement(), question.getId() , question.getQuestioner().getId());

        return new ResponseEntity<>(questionDto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> postQuestion(@RequestAttribute("token") Token token, @RequestBody NewQuestionDto question) {

        Spaces space = spaceRepo.findById(question.idSpace()).get();

        List<Permission> permissions = permissionRepo.findBySpaceAndParticipant(space, userRepo.findById(token.getId()).get());

        if(permissions.isEmpty())
            return new ResponseEntity<>("Voce não tem essa permissao", HttpStatus.FORBIDDEN);

        questionService.createQuestion(question.statement(), userRepo.findById(token.getId()).get());

        return new ResponseEntity<>("Pergunta enviada", HttpStatus.OK);

        // {
        //     "statement": "Como entro na ets?",
        //     "idSpace": 2
        // }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@RequestAttribute ("token") Token token, @PathVariable Long id){

        Optional<User> users = userRepo.findById(token.getId());

        List<Permission> permissions = permissionRepo.findByParticipant(users.get());

        if(!questionService.deleteQuestion(id, permissions.get(0)))
            return new ResponseEntity<>("Erro deletando a pergunta", HttpStatus.NOT_ACCEPTABLE);
            
        return new ResponseEntity<>("Pergunta deletada", HttpStatus.OK);
    }
}
