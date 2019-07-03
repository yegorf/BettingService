package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.entities.Sport;
import com.yegorf.bookmaker.entities.Team;
import com.yegorf.bookmaker.exceptions.AlreadyExistException;
import com.yegorf.bookmaker.repos.SportRepo;
import com.yegorf.bookmaker.repos.TeamRepo;
import com.yegorf.bookmaker.unique_validators.DirectoriesValidator;
import org.springframework.web.bind.annotation.*;

import java.util.TreeSet;

@RestController
@RequestMapping("directories")
public class DirectoriesController {
    private final SportRepo sportRepo;
    private final TeamRepo teamRepo;

    public DirectoriesController(SportRepo sportRepo, TeamRepo teamRepo) {
        this.sportRepo = sportRepo;
        this.teamRepo = teamRepo;
    }

    @GetMapping("/getTeams")
    public TreeSet<Team> getTeams() {
        return teamRepo.findAll();
    }

    @PostMapping("/addTeam")
    public String addTeam(@RequestParam String team, @RequestParam String info) throws AlreadyExistException {
        new DirectoriesValidator(sportRepo, teamRepo).checkTeamUniq(team);
        teamRepo.save(new Team(team, info));
        return "Added!";
    }

    @PostMapping("/removeTeam")
    public void removeTeam(@RequestParam int team) {
        teamRepo.deleteById(team);
    }

    @GetMapping("/getSports")
    public TreeSet<Sport> getSports() {
        return sportRepo.findAll();
    }

    @PostMapping("/addSport")
    public String addSport(@RequestParam String sport) throws AlreadyExistException {
        new DirectoriesValidator(sportRepo, teamRepo).checkSportUniq(sport);
        sportRepo.save(new Sport(sport));
        return "Added!";
    }

    @PostMapping("/removeSport")
    public void removeSport(@RequestParam int sport) {
        sportRepo.deleteById(sport);
    }
}
