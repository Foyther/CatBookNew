package com.example.catbooknew.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String username;

    @NotNull
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<CatPair> catPairs;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public User() {
    }
}
