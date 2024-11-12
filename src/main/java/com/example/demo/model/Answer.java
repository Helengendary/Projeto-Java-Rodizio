package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name= "tbAnswer")
public class Answer {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String answer;

    // FOREIGN KEY DE FILHO
    @ManyToOne
    @JoinColumn
    private Set<Permission> respondent = new HashSet<>();

    @ManyToOne
    @JoinColumn
    private Set<Question> question = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Set<Permission> getRespondent() {
        return respondent;
    }

    public void setRespondent(Set<Permission> respondent) {
        this.respondent = respondent;
    }

    public Set<Question> getQuestion() {
        return question;
    }

    public void setQuestion(Set<Question> question) {
        this.question = question;
    }

}
