package com.yegorf.bookmaker.repos;

import com.yegorf.bookmaker.entities.Event;
import com.yegorf.bookmaker.entities.Sport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;

@Repository
public interface EventRepo extends CrudRepository<Event, Integer> {
    HashSet<Event> findAll();
    HashSet<Event> findAllBySport(Sport sport);
    Event findById(int id);
   // Event findById(Integer id);
}
