package com.example.catbooknew.service;

import com.example.catbooknew.dto.Cat;
import com.example.catbooknew.repository.CatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CatService {
    private CatRepository catRepository;
    public void updateCatRating(Integer catId){
        Cat cat = catRepository.findById(catId).orElseThrow(() ->
                new IllegalArgumentException("Cat doesn't exist"));
        cat.setNumOfVoices(cat.getNumOfVoices() + 1);
        catRepository.save(cat);
    }

    public Cat findById(Integer id){
        return catRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Cat doesn't exist"));
    }

    public List<Cat> findTop10ByOrderByNumOfVoicesDesc() {
        List<Cat> cats = catRepository.findTop10ByOrderByNumOfVoicesDesc();
        if (cats.isEmpty()){
            throw new IllegalArgumentException("Cat's rating is not available");
        }
        return cats;
    }
}
