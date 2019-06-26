package com.yegorf.bookmaker.repos;

import com.yegorf.bookmaker.entities.Transaction;
import com.yegorf.bookmaker.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface TransactionRepo extends CrudRepository<Transaction, Integer> {
    HashSet<Transaction> findAllByUser(User user);
}
