package com.yegorf.bookmaker.repos;

import com.yegorf.bookmaker.entities.Event;
import com.yegorf.bookmaker.entities.EventResult;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface EventResultRepo extends CrudRepository<EventResult, Integer> {
    HashSet<EventResult> findAll();
    HashSet<EventResult> findAllByEvent(Event event);
}
