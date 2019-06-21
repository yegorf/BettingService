package com.yegorf.bookmaker.validators;

import com.yegorf.bookmaker.entities.User;
import com.yegorf.bookmaker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginValidator {
    private final UserRepo userRepo;

    public LoginValidator(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void validate(String username, String password) throws Exception {
        checkUsername(username);
        checkPassword(password);
    }

    private void checkUsername(String username) throws Exception {
        boolean usernameCheck = false;

        for(User user : userRepo.findAll()) {
            if(username.equals(user.getName())) {
                usernameCheck = true;
                break;
            }
        }
        if(!usernameCheck) {
            throw new Exception("Wrong username!");
        }
    }

    private void checkPassword(String password) throws Exception {
            boolean passwordCheck = false;

            for(User user : userRepo.findAll()) {
                if(password.equals(user.getPassword())) {
                    passwordCheck = true;
                    break;
                }
            }
            if(!passwordCheck) {
                throw new Exception("Wrong password!");
            }
    }
}
