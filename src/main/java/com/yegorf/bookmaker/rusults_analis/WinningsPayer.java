package com.yegorf.bookmaker.rusults_analis;

import com.yegorf.bookmaker.entities.Bet;
import com.yegorf.bookmaker.entities.EventResult;
import com.yegorf.bookmaker.entities.User;
import com.yegorf.bookmaker.enums.BetStatus;
import com.yegorf.bookmaker.repos.BetRepo;
import com.yegorf.bookmaker.repos.UserRepo;

import java.util.HashSet;

public class WinningsPayer {
    private final BetRepo betRepo;
    private final UserRepo userRepo;

    public WinningsPayer(BetRepo betRepo, UserRepo userRepo) {
        this.betRepo = betRepo;
        this.userRepo = userRepo;
    }

    public void pay(EventResult result) {
        HashSet<Bet> bets = betRepo.findAllByEventResult(result);
        for(Bet bet : bets) {
            User user = bet.getUser();
            user.setBalance(user.getBalance() + (bet.getSum() * bet.getCoef()));
            userRepo.save(user);
            bet.setStatus(BetStatus.WON.name());
        }
    }
}
