package com.yegorf.bookmaker.repos;

import com.yegorf.bookmaker.entities.Sport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepo extends CrudRepository<Sport, Integer> {
}
