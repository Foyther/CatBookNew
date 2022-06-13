package com.example.catbooknew.controller;

import com.example.catbooknew.dto.Role;
import com.example.catbooknew.dto.Status;
import com.example.catbooknew.dto.User;
import com.example.catbooknew.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class AuthController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String welcome() {
        return "welcomePage";
    }

    @GetMapping({"/sign-in"})
    public String signIn() {
        return "signInPage";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "signUpPage";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String name, String password) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException();
        }
        
        if (userService.userExistByUsername(name)) {
            return "redirect:/sign-up";
        }

        User user = new User();
        user.setUsername(name);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(password));
        user.setStatus(Status.ACTIVE);
        userService.save(user);
        return "redirect:/sign-in";
    }
}
