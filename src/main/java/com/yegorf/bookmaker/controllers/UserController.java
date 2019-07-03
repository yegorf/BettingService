package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.email.EmailProperties;
import com.yegorf.bookmaker.email.EmailSender;
import com.yegorf.bookmaker.entities.User;
import com.yegorf.bookmaker.enums.UserRole;
import com.yegorf.bookmaker.exceptions.NotExistException;
import com.yegorf.bookmaker.repos.UserRepo;
import com.yegorf.bookmaker.unique_validators.LoginValidator;
import com.yegorf.bookmaker.unique_validators.RegistrationValidator;
import com.yegorf.bookmaker.validators.EmailValidator;
import com.yegorf.bookmaker.validators.PasswordValidator;
import com.yegorf.bookmaker.validators.UsernameValidator;
import com.yegorf.bookmaker.validators.Validator;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.util.HashSet;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepo userRepo;
    private Validator validator = new Validator();

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
        validator.setValidationStrategy(new UsernameValidator());
        validator.validate(username);
        validator.setValidationStrategy(new PasswordValidator());
        validator.validate(password);
        validator.setValidationStrategy(new EmailValidator());
        validator.validate(email);

        User user = new User(username, password, email);
        user.setBalance(0.0f);
        new RegistrationValidator(userRepo).dataUniqCheck(user);
        user.setAdmin(UserRole.USER.name());
        userRepo.save(user);
        return "Registration completed!";
    }

    @PostMapping("/login")
    public Object login(@RequestParam String username,
                        @RequestParam String password) throws NotExistException {
        return new LoginValidator(userRepo).validate(username, password);
    }

    @GetMapping("/getAdmins")
    public HashSet<User> getAdmins() {
        return userRepo.findAllByAdmin(UserRole.ADMIN.name());
    }

    @PostMapping("/makeAdmin")
    public void makeAdmin(@RequestParam int id) {
        User user = userRepo.findById(id);
        user.setAdmin(UserRole.ADMIN.name());
        userRepo.save(user);
    }

    @PostMapping("/removeAdmin")
    public void removeAdmin(@RequestParam int id) {
        User user = userRepo.findById(id);
        user.setAdmin(UserRole.USER.name());
        userRepo.save(user);
    }

    @PostMapping("/remindPassword")
    public void remindPassword(@RequestParam int userId) {
        User user = userRepo.findById(userId);
        EmailSender emailSender = new EmailSender(EmailProperties.EMAIL, EmailProperties.PASSWORD);
        emailSender.send("Password remind", "Your password: " + user.getPassword(), user.getEmail());
    }
}
