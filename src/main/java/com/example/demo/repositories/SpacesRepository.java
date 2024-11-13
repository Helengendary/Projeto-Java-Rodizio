package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import com.example.demo.model.Spaces;
import com.example.demo.model.User;

@Repository
public interface SpacesRepository extends JpaRepository<Spaces, Long>{

    @Query
    ("SELECT s from tbSpace s WHERE s.name like '%?1%'")
    List<Spaces> queryFindByName (String query, Pageable pageable);

    List<Spaces> findByOwner(User owner);
}
