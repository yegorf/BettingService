package com.yegorf.bookmaker.coefs;

import com.yegorf.bookmaker.dto.ResponseResult;
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
    private HashSet<ResponseResult> results = new HashSet<ResponseResult>();

    public ResultCalculator(BetRepo betRepo, EventResultRepo eventResultRepo) {
        this.betRepo = betRepo;
        this.eventResultRepo = eventResultRepo;
    }

    public HashSet<ResponseResult> setSumCoef(Event event) {
        HashSet<EventResult> eventResults = eventResultRepo.findAllByEvent(event);
        float totalSum = setSums(eventResults);
        setPercents(totalSum);
        return results;
    }

    private float setSums(HashSet<EventResult> eventResults) {
        float totalSum = 0.0f;
        for (EventResult result : eventResults) {
            ResponseResult resultsSumCoef = new ResponseResult();

            resultsSumCoef.setId(result.getId());
            resultsSumCoef.setName(result.getResult());

            float sum = 0.0f;
            for (Bet bet : betRepo.findAllByEventResult(result)) {
                sum += bet.getSum();
            }

            resultsSumCoef.setSum(sum);
            results.add(resultsSumCoef);
            totalSum += sum;
        }
        return totalSum;
    }

    private void setPercents(float totalSum) {
        for(ResponseResult result : results) {
            result.setPercent((result.getSum() / totalSum) * 100);
        }
    }
}
