package com.yegorf.bookmaker.repos;

import com.yegorf.bookmaker.entities.Bet;
import com.yegorf.bookmaker.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    HashSet<User> findAll();
}
