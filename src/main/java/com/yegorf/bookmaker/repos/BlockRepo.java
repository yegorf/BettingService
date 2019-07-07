package com.yegorf.bookmaker.repos;

import com.yegorf.bookmaker.entities.Block;
import com.yegorf.bookmaker.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.TreeSet;

public interface BlockRepo extends CrudRepository<Block, Integer> {
    HashSet<Block> findAll();

    HashSet<Block> findAllByUser(User user);
}
