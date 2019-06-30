package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.coefs.ResultCalculator;
import com.yegorf.bookmaker.dto.ResultsSumCoef;
import com.yegorf.bookmaker.entities.Event;
import com.yegorf.bookmaker.repos.BetRepo;
import com.yegorf.bookmaker.repos.EventRepo;
import com.yegorf.bookmaker.repos.EventResultRepo;
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

    public ResultController(EventResultRepo eventResultRepo, EventRepo eventRepo, BetRepo betRepo) {
        this.eventResultRepo = eventResultRepo;
        this.eventRepo = eventRepo;
        this.betRepo = betRepo;
    }

    @PostMapping("getEventResults")
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
}
