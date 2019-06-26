package com.yegorf.bookmaker.coefs;

import com.yegorf.bookmaker.dto.ResultsSumCoef;
import com.yegorf.bookmaker.entities.Bet;
import com.yegorf.bookmaker.entities.Event;
import com.yegorf.bookmaker.entities.EventResult;
import com.yegorf.bookmaker.repos.BetRepo;
import com.yegorf.bookmaker.repos.EventResultRepo;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class ResultCalculator {
    private final BetRepo betRepo;
    private final EventResultRepo eventResultRepo;
    private HashSet<ResultsSumCoef> results = new HashSet<ResultsSumCoef>();

    public ResultCalculator(BetRepo betRepo, EventResultRepo eventResultRepo) {
        this.betRepo = betRepo;
        this.eventResultRepo = eventResultRepo;
    }


    public HashSet<ResultsSumCoef> setSums(Event event) {
        for(EventResult result : eventResultRepo.findAllByEvent(event)){
            ResultsSumCoef resultsSumCoef = new ResultsSumCoef();
            resultsSumCoef.setId(result.getId());
            resultsSumCoef.setName(result.getResult());
            float sum = 0.0f;
            for(Bet bet : betRepo.findAllByEventResult(result)) {
                sum+=bet.getSum();
            }
            resultsSumCoef.setSum(sum);
            results.add(resultsSumCoef);
        }

        return results;
    }


}
