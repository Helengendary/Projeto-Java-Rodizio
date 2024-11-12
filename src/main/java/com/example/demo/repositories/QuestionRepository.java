package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Question;
import com.example.demo.model.User;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
    @Query("SELECT q FROM tbQuestion q INNER JOIN tbPermission p ON P.id = q.questioner WHERE p.participant = :user")
    Question findByUser(@Param("user") User user);
}
