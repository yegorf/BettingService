package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.entities.User;
import com.yegorf.bookmaker.repos.UserRepo;
import com.yegorf.bookmaker.validators.LoginValidator;
import com.yegorf.bookmaker.validators.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/getUsers")
    public HashSet<User> getUser() {
        HashSet<User> users = userRepo.findAll();
        for(User user : users) {
            System.out.println(user.getId() + user.getEmail());
        }
        return users;
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String username,
                        @RequestParam String password,
                        @RequestParam String email) {
        User user = new User(username, password, email);
        try {
            new RegistrationValidator(userRepo).dataUniqCheck(user);
        } catch (Exception e) {
            System.out.println(e.toString());
            return e.getMessage();
        }
        userRepo.save(user);
        return "Registration completed!";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                      @RequestParam String password) {
        try {
            new LoginValidator(userRepo).validate(username, password);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Login completed!";
    }
}
