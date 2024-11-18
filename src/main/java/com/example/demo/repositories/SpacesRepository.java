package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Spaces;
import com.example.demo.model.User;

@Repository
public interface SpacesRepository extends JpaRepository<Spaces, Long>{

    @Query(value = "SELECT * FROM tb_space WHERE name LIKE %:query% ORDER BY id OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", nativeQuery = true)
    List<Spaces> queryFindByName(@Param("query") String query, @Param("offset") int offset, @Param("limit") int limit);

    List<Spaces> findByOwner(User owner);
    List<Spaces> findByName(String name);
}
