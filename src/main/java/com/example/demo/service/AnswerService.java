package com.example.demo.service;

import com.example.demo.model.Question;
import com.example.demo.model.User;

public interface AnswerService {
    Boolean createAnswer(String answer, User respondent, Question question);
    Boolean updateAnswer(String newAnswer, User respondent, Integer questionId);
    Boolean deleteAnswer(User respondent, Integer questionId);
}
