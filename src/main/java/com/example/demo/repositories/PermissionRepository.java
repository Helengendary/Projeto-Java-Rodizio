package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Permission;
import com.example.demo.model.Spaces;
import com.example.demo.model.User;

import java.util.List;


@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    List<Permission> findBySpace(Spaces space);
    // List<Permission> findByparticipant(User user);
    List<Permission> findBySpaceAndParticipant(Spaces space, User user);
}
