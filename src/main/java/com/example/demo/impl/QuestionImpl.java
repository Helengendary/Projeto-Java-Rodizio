package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Permission;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.service.QuestionService;

public class QuestionImpl implements QuestionService{

    @Autowired
    QuestionRepository repository;

    @Override
    public Boolean createQuestion(String statement, User user) {

        

        Question question = new Question();

        question.setQuestioner(user);

        question.setStatement(statement);

        repository.save(question);

        return true;
    }

    
    @Override
    public Boolean deleteQuestion(Long questionId, Permission permission) {

        if(!permission.getAdm())
            return false;

        Question question = repository.findById(questionId).get();
        
        repository.delete(question);
        
        return true;
    }
    
    @Override
    public Boolean editQuestion(Integer questionid, Permission permission, String newStatement) {
        return true;
    }

}
