package com.example.demo.service;

import com.example.demo.model.Permission;

public interface QuestionService {
    Boolean createQuestion(String statement, Permission permission);
    Boolean editQuestion(Integer questionid, Permission permission, String newStatement);
    Boolean deleteQuestion(Integer questionid, Permission permission);
}
