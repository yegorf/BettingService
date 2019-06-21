package com.yegorf.bookmaker.repos;

import com.yegorf.bookmaker.entities.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepo extends CrudRepository<Team, Integer> {
}
