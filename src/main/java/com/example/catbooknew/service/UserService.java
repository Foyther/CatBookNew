package com.example.catbooknew.service;

import com.example.catbooknew.dto.CatPair;
import com.example.catbooknew.dto.User;
import com.example.catbooknew.repository.CatPairRepository;
import com.example.catbooknew.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
    CatPairRepository catPairRepository;
    UserRepository userRepository;

    public void updateChosenPairForUser(User user, Integer catPairId){
        Set<CatPair> catPairs = user.getCatPairs();
        CatPair userPair = catPairRepository.findById(catPairId).get();
        if (catPairs.contains(userPair)){
            throw new IllegalArgumentException("Repeated pair of cats");
        }
        catPairs.add(userPair);
        userRepository.save(user);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User doesn't exist"));
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
