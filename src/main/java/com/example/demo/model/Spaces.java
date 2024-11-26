package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table(name= "tbSpace")
public class Spaces {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    // DEIXA ISSO AQUI MARIANA
    // FOREIGN KEY DE FILHO
    @ManyToOne
    @JoinColumn
    private User owner;

    // FOREIGN KEY DE PAI
    // @OneToMany(mappedBy = "space")
    // private Set<Permission> permission = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    // public Set<Permission> getPermission() {
    //     return permission;
    // }

    // public void setPermission(Set<Permission> permission) {
    //     this.permission = permission;
    // }
}
