package com.example.catbooknew.controller;

import com.example.catbooknew.dto.Cat;
import com.example.catbooknew.dto.CatPair;
import com.example.catbooknew.dto.User;
import com.example.catbooknew.model.ChosenImageInfo;
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

import java.util.List;
import java.util.Optional;

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
        Optional<User> optionalUser = userRepository.findById(1);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User is not authorized");
        }
        User user = optionalUser.get();
        List<CatPair> catPairs = catPairRepository
                .findAllWithoutUsedPairs(userRepository.findCatPairsIdByUserId(user.getId()));
        CatPair catPair = catPairs.get((int) (Math.random() * (catPairs.size() - 1) + 1));

        Cat cat1 = catRepository.findById(catPair.getFirstCatId()).get();
        Cat cat2 = catRepository.findById(catPair.getSecondCatId()).get();

        map.put("catPairId", catPair.getId());
        map.put("first_cat", cat1);
        map.put("second_cat", cat2);

        return "HomePage";
    }

    @PostMapping("/chosen")
    public String chosen(@RequestBody ChosenImageInfo chosenImageInfo) {
        Optional<User> optionalUser = userRepository.findById(1);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User is not authorized");
        }
        userService.chosenPairForUser(optionalUser.get(), chosenImageInfo.getCatPairId());
        catService.updateCatRating(chosenImageInfo.getChoosedCatId());
        return "";
    }
}
