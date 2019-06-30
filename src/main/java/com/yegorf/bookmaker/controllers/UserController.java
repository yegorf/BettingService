package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.entities.Role;
import com.yegorf.bookmaker.entities.User;
import com.yegorf.bookmaker.exceptions.AlreadyExistException;
import com.yegorf.bookmaker.exceptions.NotExistException;
import com.yegorf.bookmaker.repos.UserRepo;
import com.yegorf.bookmaker.unique_validators.LoginValidator;
import com.yegorf.bookmaker.unique_validators.RegistrationValidator;
import com.yegorf.bookmaker.validators.EmailValidator;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/getUsers")
    public HashSet<User> getUser() {
        return userRepo.findAll();
    }

    @PostMapping("/getUser")
    public User getUser(@RequestParam String name) {
        return userRepo.findByName(name);
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String email) throws Exception {
        EmailValidator.check(email);

        User user = new User(username, password, email);
        new RegistrationValidator(userRepo).dataUniqCheck(user);
        userRepo.save(user);
        return "Registration completed!";
    }

    @PostMapping("/login")
    public Object login(@RequestParam String username,
                        @RequestParam String password) throws NotExistException {
        User user;
        user = new LoginValidator(userRepo).validate(username, password);
        return user;
    }

    @GetMapping("/getAdmins")
    public HashSet<User> getAdmins() {
        return userRepo.findAllByAdmin(Role.ADMIN.getCode());
    }

    @PostMapping("/makeAdmin")
    public void makeAdmin(@RequestParam int id) {
        User user = userRepo.findById(id);
        user.setAdmin(1);
        userRepo.save(user);
    }

    @PostMapping("/removeAdmin")
    public void removeAdmin(@RequestParam int id) {
        User user = userRepo.findById(id);
        user.setAdmin(0);
        userRepo.save(user);
    }
}
