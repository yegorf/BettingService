package com.yegorf.bookmaker.validators;

import com.yegorf.bookmaker.entities.User;
import com.yegorf.bookmaker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginValidator {
    private final UserRepo userRepo;
    private User user;

    public LoginValidator(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User validate(String username, String password) throws Exception {
        checkUsername(username);
        checkPassword(password);
        return user;
    }

    private void checkUsername(String username) throws Exception {
        boolean usernameCheck = false;

        for(User user : userRepo.findAll()) {
            if(username.equals(user.getName())) {
                usernameCheck = true;
                this.user = user;
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
                    this.user = user;
                    break;
                }
            }
            if(!passwordCheck) {
                throw new Exception("Wrong password!");
            }
    }
}
