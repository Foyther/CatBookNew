package com.example.catbooknew.repository;

import com.example.catbooknew.dto.Cat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CatRepository extends CrudRepository<Cat, Integer> {
    @Override
    Optional<Cat> findById(Integer integer);

    @Override
    <S extends Cat> S save(S entity);

    @Query(value = "SELECT c FROM Cat c ORDER BY c.numOfVoices DESC")
    Optional<List<Cat>> getPopularCatInTheAmountOf();
}
