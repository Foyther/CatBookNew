package com.example.catbooknew.service;

import com.example.catbooknew.repository.CatPairRepository;
import com.example.catbooknew.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CatPairServiceTest {

    @Autowired
    private CatPairService catPairService;

    @MockBean
    private CatPairRepository catPairRepository;
    @MockBean
    private UserRepository userRepository;

    @Test
    void findAllWithoutUsedPairs_whenUsersCatPairsIsNotEmpty_findUserPairsWithoutUsersCatPairs(){
        Set<Integer> userCatPairId = Set.of(1);
        doReturn(userCatPairId)
                .when(userRepository)
                .findCatPairsIdByUserId(1);
        catPairService.findAllWithoutUsedPairs(1);

        verify(catPairRepository, times(1)).findAllWithoutUsedPairs(userCatPairId);
    }

    @Test
    void findAllWithoutUsedPairs_whenUsersCatPairsIsEmpty_findAllUserPairs(){
        Set<Integer> userCatPairId = new HashSet<>();
        doReturn(userCatPairId)
                .when(userRepository)
                .findCatPairsIdByUserId(1);
        catPairService.findAllWithoutUsedPairs(1);

        verify(catPairRepository, times(1)).findAll();
    }
}