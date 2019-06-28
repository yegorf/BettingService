package com.yegorf.bookmaker.validators;

import com.yegorf.bookmaker.entities.User;
import com.yegorf.bookmaker.exceptions.NotExistException;
import com.yegorf.bookmaker.repos.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class LoginValidator {
    private final UserRepo userRepo;
    private User user;

    public LoginValidator(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User validate(String username, String password) throws NotExistException {
        checkUsername(username);
        checkPassword(username, password);
        return user;
    }

    private void checkUsername(String username) throws NotExistException {
        boolean usernameCheck = false;

        for (User user : userRepo.findAll()) {
            if (username.equals(user.getName())) {
                usernameCheck = true;
                this.user = user;
                break;
            }
        }
        if (!usernameCheck) {
            throw new NotExistException("Wrong username!");
        }
    }

    private void checkPassword(String username, String password) throws NotExistException {
        boolean passwordCheck = false;

        for (User user : userRepo.findAll()) {
            if (username.equals(user.getName()) && password.equals(user.getPassword())) {
                passwordCheck = true;
                this.user = user;
                break;
            }
        }
        if (!passwordCheck) {
            throw new NotExistException("Wrong password!");
        }
    }
}
