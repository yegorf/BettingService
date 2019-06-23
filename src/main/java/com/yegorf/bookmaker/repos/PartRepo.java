package com.yegorf.bookmaker.repos;

import com.yegorf.bookmaker.entities.Event;
import com.yegorf.bookmaker.entities.Part;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Repository
public interface PartRepo extends CrudRepository<Part, Integer> {
    HashSet<Part> findAllByEvent(Event event);
}
