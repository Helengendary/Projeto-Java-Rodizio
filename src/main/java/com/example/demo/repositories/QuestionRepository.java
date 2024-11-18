package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Question;
import com.example.demo.model.Spaces;
import com.example.demo.model.User;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{
    // @Query(value="SELECT q FROM tb_question q INNER JOIN q.permission p WHERE p.id = q.permission.id AND p.participant = :user", nativeQuery=true)
    // Question findByUser(@Param("user") User user);

    @Query(value = "SELECT q.id, q.statement, q.questioner_id FROM tb_question q JOIN tb_permission p ON p.id = q.questioner_id JOIN tb_space s ON s.id = p.space_id WHERE s.id = :idSpace ORDER BY q.id OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", nativeQuery = true)
    List<Question> findBySpace(@Param("idSpace") Long idSpace, @Param("offset") int offset, @Param("limit") int limit);
}
