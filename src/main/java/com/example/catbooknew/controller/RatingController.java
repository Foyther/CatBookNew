package com.example.catbooknew.controller;

import com.example.catbooknew.dto.Cat;
import com.example.catbooknew.repository.CatPairRepository;
import com.example.catbooknew.repository.CatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/choose")
public class RatingController {

    private CatRepository catRepository;
    private CatPairRepository catPairRepository;

    @RequestMapping("")
    public String showRating(ModelMap map) {
        List<Cat> topTen = catRepository.getTopBy();
        map.put("top_ten", topTen);
        return "";
    }

}