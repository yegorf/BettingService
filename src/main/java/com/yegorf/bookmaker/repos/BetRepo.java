package com.yegorf.bookmaker.repos;

import com.yegorf.bookmaker.entities.Bet;
import com.yegorf.bookmaker.entities.EventResult;
import com.yegorf.bookmaker.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface BetRepo extends CrudRepository<Bet, Integer> {
    HashSet<Bet> findAllByUser(User user);

    HashSet<Bet> findAllByEventResult(EventResult eventResult);
}
