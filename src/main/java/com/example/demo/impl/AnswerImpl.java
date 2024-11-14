package com.example.demo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.Answer;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.repositories.AnswerRepository;
import com.example.demo.service.AnswerService;

public class AnswerImpl implements AnswerService {

    @Autowired
    AnswerRepository answerRepo;

    @Override
    public Boolean createAnswer(String answer, User respondent, Question question) {

        Answer newAnswer = new Answer();

        newAnswer.setAnswer(answer);
        newAnswer.setRespondent(respondent);
        newAnswer.setQuestion(question);

        answerRepo.save(newAnswer);

        return true;
    }

    @Override
    public Boolean updateAnswer(String newAnswer, User respondent, Integer questionId) {

    }

    @Override
    public Boolean deleteAnswer(User respondent, Integer questionId) {
     
        answerRepo.findById(null)
    }
    
}
