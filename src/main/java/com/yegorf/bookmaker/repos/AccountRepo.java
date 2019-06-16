package com.yegorf.bookmaker.repos;

import com.yegorf.bookmaker.entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends CrudRepository<Account, Integer> {
}
