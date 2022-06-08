package com.example.catbooknew.controller;

import com.example.catbooknew.dto.Cat;
import com.example.catbooknew.service.CatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/rating")
public class RatingController {
    private CatService catService;
    @GetMapping
    public String showRating(ModelMap map) {
        List<Cat> cats = catService.findByOrderByNumOfVoicesDesc();
        map.put("top_ten", cats);
        return "showRating";
    }
}