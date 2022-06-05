package com.example.catbooknew.repository;

import com.example.catbooknew.dto.Cat;
import com.example.catbooknew.dto.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CatRepository extends CrudRepository<Cat, Integer> {
    @Override
    Optional<Cat> findById(Integer integer);

    @Override
    <S extends Cat> S save(S entity);

    List<Cat> getTopBy();
}
