package com.yegorf.bookmaker.validators;

import com.yegorf.bookmaker.entities.Sport;
import com.yegorf.bookmaker.entities.Team;
import com.yegorf.bookmaker.repos.SportRepo;
import com.yegorf.bookmaker.repos.TeamRepo;
import org.springframework.stereotype.Service;

@Service
public class DirectoriesValidator {
    private final SportRepo sportRepo;
    private final TeamRepo teamRepo;

    public DirectoriesValidator(SportRepo sportRepo, TeamRepo teamRepo) {
        this.sportRepo = sportRepo;
        this.teamRepo = teamRepo;
    }

    public void checkSportUniq(String sport) throws Exception {
        for(Sport s : sportRepo.findAll()) {
            if(sport.equals(s.getSport())) {
                throw new Exception("This sport already exists!");
            }
        }
    }

    public void checkTeamUniq(String team) throws Exception {
        for(Team t : teamRepo.findAll()) {
            if(team.equals(t.getName())) {
                throw  new Exception("This team already exists!");
            }
        }
    }
}
