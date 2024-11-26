package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "tbQuestion")
public class Question {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String statement;

    // FOREIGN KEY DE FILHO
    @ManyToOne
    @JoinColumn
    private User questioner;
    
    // FOREIGN KEY DE PAI
    // @OneToMany(mappedBy = "question")
    // private Set<Answer> answer = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    // public Set<Answer> getAnswer() {
    //     return answer;
    // }

    // public void setAnswer(Set<Answer> answer) {
    //     this.answer = answer;
    // }

    public User getQuestioner() {
        return questioner;
    }

    public void setQuestioner(User questioner) {
        this.questioner = questioner;
    }
}
