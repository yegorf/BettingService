package com.yegorf.bookmaker.transactions;

import com.yegorf.bookmaker.entities.Transaction;
import com.yegorf.bookmaker.entities.User;
import com.yegorf.bookmaker.repos.TransactionRepo;
import com.yegorf.bookmaker.repos.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Test example
@Service
public class TransactionHandler {
    private final TransactionRepo transactionRepo;
    private final UserRepo userRepo;

    public TransactionHandler(TransactionRepo transactionRepo, UserRepo userRepo) {
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
    }

    public void addTransaction(String username, float sum) {
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        User user = userRepo.findByName(username);
        Transaction transaction = new Transaction(user, sum, date, time);
        transactionRepo.save(transaction);
        user.setBalance(user.getBalance() + sum);
        userRepo.save(user);
    }
}
