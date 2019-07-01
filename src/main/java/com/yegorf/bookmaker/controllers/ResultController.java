package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.coefs.ResultCalculator;
import com.yegorf.bookmaker.dto.ResultsSumCoef;
import com.yegorf.bookmaker.entities.Event;
import com.yegorf.bookmaker.entities.EventResult;
import com.yegorf.bookmaker.repos.BetRepo;
import com.yegorf.bookmaker.repos.EventRepo;
import com.yegorf.bookmaker.repos.EventResultRepo;
import com.yegorf.bookmaker.repos.UserRepo;
import com.yegorf.bookmaker.rusults_analis.WinningsPayer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

@RestController
@RequestMapping("/results")
public class ResultController {
    private final EventResultRepo eventResultRepo;
    private final EventRepo eventRepo;
    private final BetRepo betRepo;
    private final UserRepo userRepo;

    public ResultController(EventResultRepo eventResultRepo, EventRepo eventRepo, BetRepo betRepo, UserRepo userRepo) {
        this.eventResultRepo = eventResultRepo;
        this.eventRepo = eventRepo;
        this.betRepo = betRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/getEventResults")
    public HashSet<ResultsSumCoef> getEventResults(@RequestParam Integer id) {
        ResultCalculator calculator = new ResultCalculator(betRepo, eventResultRepo);
        HashSet<ResultsSumCoef> results = null;

        for (Event event : eventRepo.findAll()) {
            if (event.getId().equals(id)) {
                results = calculator.setSums(event);
                break;
            }
        }

        assert results != null;
        for (ResultsSumCoef result : results) {
            System.out.println("res " + result.getId() + " " + result.getSum() + " " + result.getName());
        }

        return results;
    }

    @PostMapping("/setEventResult")
    public void setEventResult(@RequestParam int eventId,
                               @RequestParam int resultId) {
        Event event = eventRepo.findById(eventId);
        event.setActive(0);
        eventRepo.save(event);

        EventResult wonResult = null;
        for(EventResult result : eventResultRepo.findAllByEvent(event)) {
            if(result.getId() == resultId) {
                result.setWon(1);
                wonResult = result;
            } else {
                result.setWon(0);
            }
            eventResultRepo.save(result);
        }

        new WinningsPayer(betRepo, userRepo).pay(wonResult);
    }
}
