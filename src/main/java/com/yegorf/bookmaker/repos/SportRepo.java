package com.yegorf.bookmaker.repos;

import com.yegorf.bookmaker.entities.Sport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;

@Repository
public interface SportRepo extends CrudRepository<Sport, Integer> {
    HashSet<Sport> findAll();

    Optional<Sport> findById(Integer id);
}
