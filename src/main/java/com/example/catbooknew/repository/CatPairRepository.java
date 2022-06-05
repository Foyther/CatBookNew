package com.example.catbooknew.repository;

import com.example.catbooknew.dto.CatPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CatPairRepository extends JpaRepository<CatPair, Integer> {

    @Query(value = "SELECT cat_pairs FROM CatPair cat_pairs where cat_pairs.id not in (:catPairsId)")
    List<CatPair> findAllWithoutUsedPairs(Set<Integer> catPairsId);
}
