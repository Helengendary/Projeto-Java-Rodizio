package com.example.demo.service;

import com.example.demo.model.Question;
import com.example.demo.model.Spaces;
import com.example.demo.model.User;

public interface UserValidationServices {
    Boolean validationAnswer(User respondent, Question question); //! VERIFICA SE QUEM RESPONDEU TEM ALGUMA PERMISSÃO QUE DÁ ACESSO A SALA DE QUEM PERGUNTOU
    
    Boolean isQuestioner(User user, Question question); //! SE AQUELA PESSOA FEZ A PERGUNTA
    
    Boolean idAdm(User user, Spaces space); //! SE A PESSOA É ADM DO ESPAÇO
}
