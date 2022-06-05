package com.example.catbooknew.controller;

import com.example.catbooknew.dto.Cat;
import com.example.catbooknew.dto.CatPair;
import com.example.catbooknew.dto.User;
import com.example.catbooknew.repository.CatPairRepository;
import com.example.catbooknew.repository.CatRepository;
import com.example.catbooknew.repository.UserRepository;
import com.example.catbooknew.service.CatService;
import com.example.catbooknew.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/choose")
public class ChooseController {

    private CatRepository catRepository;
    private CatPairRepository catPairRepository;
    private UserRepository userRepository;

    private CatService catService;
    private UserService userService;

    @GetMapping
    public String showCats(ModelMap map) {
        CatPair catPair = catPairRepository.findAllOrderById();

        Cat cat1 = catRepository.findById(catPair.getFirstCatId()).get();
        Cat cat2 = catRepository.findById(catPair.getSecondCatId()).get();

        map.put("catPairId", catPair.getId());
        map.put("first_cat", cat1);
        map.put("second_cat", cat2);

        return "";
    }

    @PostMapping("/tut")
    public String chosen(@RequestBody String choosedCatId, String catPairId) {
        catService.updateCatRating(choosedCatId);
        User user = userRepository.findById(String.valueOf(1)).get();
        userService.chosenPairForUser(user, catPairId);
        return "";
    }
}
