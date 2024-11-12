package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEdv(String edv);
}
