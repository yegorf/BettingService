package com.yegorf.bookmaker.validators;

import com.yegorf.bookmaker.entities.Sport;
import com.yegorf.bookmaker.entities.Team;
import com.yegorf.bookmaker.exceptions.AlreadyExistException;
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

    public void checkSportUniq(String sport) throws AlreadyExistException {
        for (Sport s : sportRepo.findAll()) {
            if (sport.equals(s.getSport())) {
                throw new AlreadyExistException("This sport already exists!");
            }
        }
    }

    public void checkTeamUniq(String team) throws AlreadyExistException {
        for (Team t : teamRepo.findAll()) {
            if (team.equals(t.getName())) {
                throw new AlreadyExistException("This team already exists!");
            }
        }
    }
}
