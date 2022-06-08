package com.example.catbooknew.controller;

import com.example.catbooknew.dto.Cat;
import com.example.catbooknew.dto.CatPair;
import com.example.catbooknew.dto.User;
import com.example.catbooknew.service.CatPairService;
import com.example.catbooknew.service.CatService;
import com.example.catbooknew.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class ChooseController {
    private CatService catService;
    private CatPairService catPairService;
    private UserService userService;

    @GetMapping("/choose")
    public String showCats(ModelMap map, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<CatPair> catPairs = catPairService.findAllWithoutUsedPairs(user.getId());

        if (catPairs.isEmpty()) {
            return "redirect:" + MvcUriComponentsBuilder.fromMappingName("RC#showRating").build();
        }

        CatPair catPair = catPairs.get((int) (Math.random() * (catPairs.size() - 1) + 1));

        Cat cat1 = catService.findById(catPair.getFirstCatId());
        Cat cat2 = catService.findById(catPair.getSecondCatId());

        map.put("catPairId", catPair.getId());
        map.put("first_cat", cat1);
        map.put("second_cat", cat2);

        return "choose";
    }

    @PostMapping("/chosen")
    @Transactional
    public String chosen(Principal principal, @RequestParam int chosenCatId, int catPairId) {
        User user = userService.findByUsername(principal.getName());
        userService.updateChosenPairForUser(user, catPairId);
        catService.updateCatRating(chosenCatId);
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName("CC#showCats").build();
    }
}
