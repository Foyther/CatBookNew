package com.example.catbooknew.repository;

import com.example.catbooknew.dto.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Override
    Optional<User> findById(Integer s);

    @Override
    <S extends User> S save(S entity);

    @Query(value = "SELECT c.id FROM User u JOIN u.catPairs c WHERE u.id = :userId")
    Set<Integer> findCatPairsIdByUserId(Integer userId);

    Optional<User> findByUsername(String name);
}
