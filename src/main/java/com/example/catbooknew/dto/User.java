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
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<CatPair> catPairs;

    public User() {
    }
}
