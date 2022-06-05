package com.example.catbooknew.repository;

import com.example.catbooknew.dto.CatPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatPairRepository extends JpaRepository<CatPair, String> {
    @Override
    <S extends CatPair> S save(S entity);

    @Query(value = "SELECT cat_pair FROM CatPair cat_pair ORDER BY cat_pair.id")
    CatPair findAllOrderById();

}
