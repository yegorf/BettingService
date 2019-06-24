package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.entities.Bet;
import com.yegorf.bookmaker.entities.User;
import com.yegorf.bookmaker.repos.BetRepo;
import com.yegorf.bookmaker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

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

    @PostMapping("/getUserBets")
    public HashSet<Bet> getUserBets(@RequestParam String name) {
        return betRepo.findAllByUser(userRepo.findByName(name));
    }
}
