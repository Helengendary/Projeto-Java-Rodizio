package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.model.Spaces;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
    // @Query("SELECT q FROM Question q INNER JOIN q.permission p WHERE p.id = q.permission.id AND p.participant = :user")
    // Question findByUser(@Param("user") User user);

    @Query("SELECT q FROM Question q JOIN q.permission p WHERE p.space = :space")
    List<Question> findBySpace(@Param("space") Spaces space, Pageable pageable);
}
