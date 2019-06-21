package com.yegorf.bookmaker.validators;

import com.yegorf.bookmaker.entities.User;
import com.yegorf.bookmaker.repos.UserRepo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class RegistrationValidator {
    private final UserRepo userRepo;

    public RegistrationValidator(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void dataUniqCheck(User user) throws Exception {
        HashSet<User> users = userRepo.findAll();
        for(User u : users) {
            if(user.getName().equals(u.getName())) {
                throw new Exception("User with this username already exists!");
            }
            if(user.getEmail().equals(u.getEmail())) {
                throw new Exception("User with this email already exists!");
            }
        }
    }
}
