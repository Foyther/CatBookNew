package com.example.catbooknew.controller;

import com.example.catbooknew.dto.Role;
import com.example.catbooknew.dto.Status;
import com.example.catbooknew.dto.User;
import com.example.catbooknew.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class AuthController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @GetMapping({"/sign-in"})
    public String signIn(){
        return "signInPage";
    }

    @GetMapping("/sign-up")
    public String signUp(){
        return "signUpPage";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String name, String password){
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException();
        }

        Optional<User> optionalUser = userRepository.findByUsername(name);
        if (optionalUser.isPresent()){
            return "redirect:/sign-up";
        }

        User user = new User();
        user.setUsername(name);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(password));
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return "redirect:/";
    }
}
