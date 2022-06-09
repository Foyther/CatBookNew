package com.example.catbooknew.service;

import com.example.catbooknew.dto.Cat;
import com.example.catbooknew.repository.CatRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
class CatServiceTest {

    @Autowired
    private CatService catService;

    @MockBean
    private CatRepository catRepository;

    @Test
    void findTop10ByOrderByNumOfVoicesDesc_whenSuccessful_getTop10Cats() {
        List<Cat> cats = new ArrayList<>();
        cats.add(new Cat());
        Mockito.doReturn(cats)
                .when(catRepository)
                .findTop10ByOrderByNumOfVoicesDesc();

        List<Cat> top10Cats = catService.findTop10ByOrderByNumOfVoicesDesc();

        Mockito.verify(catRepository, Mockito.times(1)).findTop10ByOrderByNumOfVoicesDesc();
        Assert.assertEquals(top10Cats, cats);
    }

    @Test
    void findTop10ByOrderByNumOfVoicesDesc_whenCatsIsEmpty_throwException() {
        List<Cat> cats = new ArrayList<>();

        Mockito.doReturn(cats)
                .when(catRepository)
                .findTop10ByOrderByNumOfVoicesDesc();

        Assert.assertThrows(IllegalArgumentException.class,
                () -> catService.findTop10ByOrderByNumOfVoicesDesc());
    }

    @Test
    void findById_whenSuccess_getCat() {
        Cat cat = new Cat();

        Mockito.doReturn(Optional.of(cat))
                .when(catRepository)
                .findById(1);

        Cat foundCat = catService.findById(1);

        Assert.assertEquals(foundCat, cat);
    }

    @Test
    void findById_whenCatNotFoundById_getCat() {
        Mockito.doReturn(Optional.empty())
                .when(catRepository)
                .findById(1);

        Assert.assertThrows(IllegalArgumentException.class, () -> catService.findById(1));
    }

    @Test
    void updateCatRating_whenSuccess_getCat() {
        Cat cat = new Cat();
        cat.setNumOfVoices(5l);
        Mockito.doReturn(Optional.of(cat))
                .when(catRepository)
                .findById(1);

        catService.updateCatRating(1);

        Assert.assertEquals(cat.getNumOfVoices().longValue(), 6);
        Mockito.verify(catRepository, Mockito.times(1)).save(cat);
    }

    @Test
    void updateCatRating_whenCatNotFoundById_getCat() {
        Mockito.doReturn(Optional.empty())
                .when(catRepository)
                .findById(1);

        Assert.assertThrows(IllegalArgumentException.class, () -> catService.updateCatRating(1));
    }
}