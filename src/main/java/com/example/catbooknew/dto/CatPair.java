package com.example.catbooknew.dto;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "cat_pairs")
public class CatPair implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @NotNull
    private Integer firstCatId;

    @NotNull
    private Integer secondCatId;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<User> users;

    public CatPair() {
    }
}
