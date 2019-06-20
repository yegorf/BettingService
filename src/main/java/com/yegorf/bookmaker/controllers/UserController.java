package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.entities.User;
import com.yegorf.bookmaker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/getUsers")
    public ArrayList getUser() {
        ArrayList<User> users = (ArrayList<User>) userRepo.findAll();
        for(User user : users) {
            System.out.println(user.getId() + user.getEmail());
        }
        return users;
    }
}
