package com.example.catbooknew.controller;

import com.example.catbooknew.dto.Cat;
import com.example.catbooknew.repository.CatPairRepository;
import com.example.catbooknew.repository.CatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/rating")
public class RatingController {

    private CatRepository catRepository;

    @RequestMapping("")
    public String showRating(ModelMap map) {
        Optional<List<Cat>> topTen = catRepository.getPopularCatInTheAmountOf();
        if (topTen.isEmpty()){
            throw new IllegalArgumentException("Not found cat's photo");
        }
        List<Cat> cats = topTen.get();
        map.put("top_ten", cats);
        return "showRating";
    }

}