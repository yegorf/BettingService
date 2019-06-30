package com.yegorf.bookmaker.repos;

import com.yegorf.bookmaker.entities.Bet;
import com.yegorf.bookmaker.entities.EventResult;
import com.yegorf.bookmaker.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    HashSet<User> findAll();

    User findByName(String name);

    User findById(int id);

    HashSet<User> findAllByAdmin(int admin);
}

//select * from users inner join event_result on
