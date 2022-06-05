package com.example.catbooknew.service;

import com.example.catbooknew.dto.Cat;
import com.example.catbooknew.repository.CatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CatService {

    private CatRepository catRepository;

    public void updateCatRating(String catId){
        Cat cat = catRepository.findById(catId).get();
        cat.setNumOfVoices(cat.getNumOfVoices() + 1);
        catRepository.save(cat);
    }
}