package com.yegorf.bookmaker.repos;

import com.yegorf.bookmaker.entities.Sport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface SportRepo extends CrudRepository<Sport, Integer> {
    HashSet<Sport> findAll();
}
