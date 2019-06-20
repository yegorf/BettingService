package com.yegorf.bookmaker.repos;

import com.yegorf.bookmaker.entities.Bet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepo extends CrudRepository<Bet, Integer> {
}
