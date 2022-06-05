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

    public void chosenPairForUser(User user, String catPairId){
        Set<CatPair> catPairs = user.getCatPairs();
        CatPair userPair = catPairRepository.findById(catPairId).get();
        catPairs.add(userPair);
        userRepository.save(user);
    }
}