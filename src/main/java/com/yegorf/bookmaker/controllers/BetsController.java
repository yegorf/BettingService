package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.entities.Bet;
import com.yegorf.bookmaker.entities.User;
import com.yegorf.bookmaker.repos.BetRepo;
import com.yegorf.bookmaker.repos.EventResultRepo;
import com.yegorf.bookmaker.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.HashSet;

@RestController
@RequestMapping("/bets")
public class BetsController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BetRepo betRepo;
    @Autowired
    private EventResultRepo eventResultRepo;

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

    @PostMapping("/addBet")
    public String addBet(@RequestParam String username,
                       @RequestParam float sum,
                       @RequestParam int eventResult
    ) {
        User user = userRepo.findByName(username);

        user.setBalance(user.getBalance() - sum);
        userRepo.save(user);

        Bet bet = new Bet();
        bet.setUser(user);
        bet.setSum(sum);
        bet.setEventResult(eventResultRepo.findById(eventResult));
        bet.setCoef(0.5f);


        betRepo.save(bet);
        return "Bet added!";
    }
}
