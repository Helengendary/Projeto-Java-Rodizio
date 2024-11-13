package com.example.demo.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEdv(String edv);

    @Query
    ("SELECT u from tbUser u where u.email like '%?1%'")
    List<User> findByQuery(String query, Pageable pageable);

    @Query
    ("select u from tbUser u where edv = :login or email = :login")
    List<User> findByLogin(@Param("login") String login);
}
