package com.example.catbooknew.repository;

import com.example.catbooknew.dto.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    @Override
    Optional<User> findById(String s);

    @Override
    <S extends User> S save(S entity);

}
