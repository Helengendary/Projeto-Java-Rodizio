package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Permission;
import com.example.demo.model.Question;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.service.QuestionService;

public class QuestionImpl implements QuestionService{

    @Autowired
    QuestionRepository repository;

    @Override
    public Boolean createQuestion(String statement, Permission permission) {

        

        Question question = new Question();

        question.setQuestioner(permission);
        question.setStatement(statement);

        repository.save(question);

        return true;
    }

    
    @Override
    public Boolean deleteQuestion(Integer questionid, Permission permission) {
        
        Question question = repository.findById(questionid.longValue()).get();
        
        repository.delete(question);
        
        return true;
    }
    
    @Override
    public Boolean editQuestion(Integer questionid, Permission permission, String newStatement) {
        return true;
    }

}
