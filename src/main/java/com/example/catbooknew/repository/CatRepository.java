package com.example.catbooknew.repository;

import com.example.catbooknew.dto.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatRepository extends JpaRepository<Cat, Integer> {
    @Override
    Optional<Cat> findById(Integer integer);

    @Override
    <S extends Cat> S save(S entity);

    List<Cat> findTop10ByOrderByNumOfVoicesDesc();
}
