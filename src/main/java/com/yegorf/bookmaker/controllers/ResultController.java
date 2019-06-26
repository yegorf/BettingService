package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.coefs.ResultCalculator;
import com.yegorf.bookmaker.dto.ResultsSumCoef;
import com.yegorf.bookmaker.entities.Event;
import com.yegorf.bookmaker.entities.EventResult;
import com.yegorf.bookmaker.repos.BetRepo;
import com.yegorf.bookmaker.repos.EventRepo;
import com.yegorf.bookmaker.repos.EventResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("/results")
public class ResultController {
    @Autowired
    EventResultRepo eventResultRepo;
    @Autowired
    EventRepo eventRepo;
    @Autowired
    BetRepo betRepo;

    @PostMapping("getEventResults")
    public HashSet<ResultsSumCoef> getEventResults(@RequestParam Integer id) {
        ResultCalculator calculator = new ResultCalculator(betRepo, eventResultRepo);
        HashSet<ResultsSumCoef> results = null;

        for(Event event : eventRepo.findAll()) {
            if(event.getId().equals(id)) {
                results = calculator.setSums(event);
                break;
            }
        }

        for(ResultsSumCoef result : results) {
            System.out.println("res " + result.getId() + " " + result.getSum() + " " + result.getName());
        }

        return results;
    }

}
