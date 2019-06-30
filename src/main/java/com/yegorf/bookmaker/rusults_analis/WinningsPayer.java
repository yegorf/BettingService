package com.yegorf.bookmaker.rusults_analis;

import com.yegorf.bookmaker.entities.Bet;
import com.yegorf.bookmaker.entities.EventResult;
import com.yegorf.bookmaker.repos.BetRepo;
import com.yegorf.bookmaker.repos.UserRepo;

public class WinningsPayer {
    private final BetRepo betRepo;
    private final UserRepo userRepo;

    public WinningsPayer(BetRepo betRepo, UserRepo userRepo) {
        this.betRepo = betRepo;
        this.userRepo = userRepo;
    }

    public void payAllByResult(EventResult result) {
        for(Bet bet : betRepo.findAllByEventResult(result)) {
            System.out.println("bet: " + bet.getId() + " user: " + bet.getUser());
        }
    }
}
