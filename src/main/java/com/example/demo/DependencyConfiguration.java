package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.dto.Token;
import com.example.demo.impl.AnswerImpl;
import com.example.demo.impl.AuthImpl;
import com.example.demo.impl.JwtImpl;
import com.example.demo.impl.PasswordEncoderImpl;
import com.example.demo.impl.PermissionImpl;
import com.example.demo.impl.QuestionImpl;
import com.example.demo.impl.SpaceImpl;
import com.example.demo.impl.UserImpl;
import com.example.demo.service.AnswerService;
import com.example.demo.service.AuthService;
import com.example.demo.service.JwtService;
import com.example.demo.service.PasswordEncoderService;
import com.example.demo.service.PermissionService;
import com.example.demo.service.QuestionService;
import com.example.demo.service.SpaceService;
import com.example.demo.service.UserService;

@Configuration
public class DependencyConfiguration {

    @Bean
    @Scope("prototype")
    public AuthService loginService(){
        return new AuthImpl();
    }

    @Bean
    @Scope("prototype")
    public AnswerService answerService(){
        return new AnswerImpl();
    }

    @Bean
    @Scope("prototype")
    public JwtService<Token> JWTService(){
        return new JwtImpl();
    }

    @Bean
    public PasswordEncoderService PasswordEncoderService(){
        return new PasswordEncoderImpl();
    }

    @Bean
    @Scope("prototype")
    public PermissionService PermissionService(){
        return new PermissionImpl();
    }

    @Bean
    @Scope("prototype")
    public QuestionService QuestionService(){
        return new QuestionImpl();
    }

    @Bean
    @Scope("prototype")
    public SpaceService SpaceService(){
        return new SpaceImpl();
    }

    @Bean
    @Scope("prototype")
    public UserService UserService(){
        return new UserImpl();
    }

}
