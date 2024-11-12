package com.example.demo.model;

import java.util.HashSet;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import java.util.Set;

@Entity
@Table(name= "tbUser")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    private String edv;
    
    @Column
    private String email;

    // FOREIGN KEY DE PAI
    @OneToMany(mappedBy = "owner")
    private Set<Spaces> spaces = new HashSet<>();

    @OneToMany(mappedBy = "participant")
    private Set<Permission> permission = new HashSet<>();
    
    public String getEdv() {
        return edv;
    }
    
    public void setEdv(String edv) {
        this.edv = edv;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Spaces> getSpaces() {
        return spaces;
    }

    public void setSpaces(Set<Spaces> spaces) {
        this.spaces = spaces;
    }

    public Set<Permission> getPermission() {
        return permission;
    }

    public void setPermission(Set<Permission> permission) {
        this.permission = permission;
    }
}