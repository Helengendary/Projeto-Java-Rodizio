package com.example.demo.service;

import com.example.demo.model.Permission;
import com.example.demo.model.User;

public interface QuestionService {
    Boolean createQuestion(String statement, User user);
    Boolean editQuestion(Integer questionid, Permission permission, String newStatement);
    Boolean deleteQuestion(Long questionid, Permission permission);
}
