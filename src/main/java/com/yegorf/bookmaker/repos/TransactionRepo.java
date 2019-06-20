package com.yegorf.bookmaker.repos;

import com.yegorf.bookmaker.entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends CrudRepository<Transaction, Integer> {
}
