package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Question;
import com.example.demo.model.Spaces;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.SpacesRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionRepository repoQuestion;

    @Autowired
    SpacesRepository repoSpace;

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
    
    
}
