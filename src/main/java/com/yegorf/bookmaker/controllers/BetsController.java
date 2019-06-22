package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.entities.Bet;
import com.yegorf.bookmaker.entities.User;
import com.yegorf.bookmaker.repos.BetRepo;
import com.yegorf.bookmaker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bets")
public class BetsController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BetRepo betRepo;

    @GetMapping("/getBets")
    public void getBets() {
        for(User user : userRepo.findAll()) {
            for(Bet bet : betRepo.findAllByUser(user)) {
                System.out.println(bet.getSum());
            }
        }
    }
}
