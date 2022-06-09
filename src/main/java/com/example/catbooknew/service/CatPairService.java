package com.example.catbooknew.service;

import com.example.catbooknew.dto.CatPair;
import com.example.catbooknew.repository.CatPairRepository;
import com.example.catbooknew.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CatPairService {
    private final CatPairRepository catPairRepository;
    private final UserRepository userRepository;

    public List<CatPair> findAllWithoutUsedPairs(Integer userId){
        Set<Integer> catPairsIdByUserId = userRepository.findCatPairsIdByUserId(userId);
        if (catPairsIdByUserId.isEmpty()){
            return catPairRepository.findAll();
        }
        return catPairRepository.findAllWithoutUsedPairs(catPairsIdByUserId);
    }
}
