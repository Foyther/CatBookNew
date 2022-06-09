package com.example.catbooknew.service;

import com.example.catbooknew.dto.CatPair;
import com.example.catbooknew.dto.User;
import com.example.catbooknew.repository.CatPairRepository;
import com.example.catbooknew.repository.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private CatPairRepository catPairRepository;
    @MockBean
    private UserRepository userRepository;

    @Test
    void updateChosenPairForUser_whenSuccessful_updateUser() {
        User user = new User();
        user.setCatPairs(new HashSet<>());

        CatPair catPair = Mockito.mock(CatPair.class);

        Optional<CatPair> optionalCatPair = Optional.of(catPair);

        Mockito.doReturn(optionalCatPair)
                .when(catPairRepository)
                .findById(2);

        userService.updateChosenPairForUser(user, 2);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void updateChosenPairForUser_whenCatPairIsContainsInUser_throwException() {
        User user = new User();
        CatPair catPair = Mockito.mock(CatPair.class);
        user.setCatPairs(Set.of(catPair));

        Optional<CatPair> optionalCatPair = Optional.of(catPair);

        Mockito.doReturn(optionalCatPair)
                .when(catPairRepository)
                .findById(2);
        Assert.assertThrows("Repeated pair of cats",
                IllegalArgumentException.class,
                () -> userService.updateChosenPairForUser(user, 2));
    }

    @Test
    void updateChosenPairForUser_whenFindCatPairByIdReturnNull_throwException() {
        User user = new User();
        CatPair catPair = Mockito.mock(CatPair.class);
        user.setCatPairs(Set.of(catPair));

        Optional<CatPair> optionalCatPair = Optional.empty();

        Mockito.doReturn(optionalCatPair)
                .when(catPairRepository)
                .findById(2);
        Assert.assertThrows("Pair of cats don't supported by id = " + 2,
                IllegalArgumentException.class,
                () -> userService.updateChosenPairForUser(user, 2));
    }

    @Test
    void updateChosenPairForUser_whenUserIsNull_throwException() {
        Assert.assertThrows("User doesn't be empty",
                IllegalArgumentException.class,
                () -> userService.updateChosenPairForUser(null, 2));
    }

    @Test
    void save_whenUserIsFull_updateUser() {
        User user = new User();
        userService.save(user);
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void save_whenUserIsNull_throwException() {
        Assert.assertThrows("Can't to update empty user",
                IllegalArgumentException.class,
                () -> userService.save(null));
    }

    @Test
    void findByUsername_whenUserIsExistByUsername_findUser() {
        User user = new User();
        String username = "username";
        Mockito.doReturn(Optional.of(user))
                .when(userRepository)
                .findByUsername(username);
        User userFoundByUsername = userService.findByUsername(username);

        Assert.assertEquals(userFoundByUsername, user);
    }

    @Test
    void findByUsername_whenUserIsNotExistByUsername_throwException() {
        String username = "username";
        Mockito.doReturn(Optional.empty())
                .when(userRepository)
                .findByUsername(username);

        Assert.assertThrows("User doesn't exist",
                IllegalArgumentException.class,
                () -> userService.findByUsername(username));
    }
}