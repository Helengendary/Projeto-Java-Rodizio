package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByEdv(String edv);

    @Query(value = "SELECT * FROM tb_user WHERE email LIKE %:query% ORDER BY id OFFSET :offset ROWS FETCH NEXT :limit ROWS ONLY", nativeQuery = true)
    List<User> findByQuery(@Param("query") String query, @Param("offset") int offset, @Param("limit") int limit);

    @Query
    ("select u from User u where edv = :login or email = :login")
    public List<User> findByLogin(@Param("login") String login);
}
