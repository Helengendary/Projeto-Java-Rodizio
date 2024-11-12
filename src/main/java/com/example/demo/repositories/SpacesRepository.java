package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Spaces;
import com.example.demo.model.User;

@Repository
public interface SpacesRepository extends JpaRepository<Spaces, Long>{
    List<Spaces> findByOwner(User owner);
}
