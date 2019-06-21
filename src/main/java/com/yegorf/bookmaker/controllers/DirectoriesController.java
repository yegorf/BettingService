package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.entities.Sport;
import com.yegorf.bookmaker.entities.Team;
import com.yegorf.bookmaker.repos.SportRepo;
import com.yegorf.bookmaker.repos.TeamRepo;
import com.yegorf.bookmaker.validators.DirectoriesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@RequestMapping("directories")
public class DirectoriesController {
    @Autowired
    private SportRepo sportRepo;
    @Autowired
    private TeamRepo teamRepo;

    private DirectoriesValidator directoriesValidator = new DirectoriesValidator(sportRepo, teamRepo);

    @GetMapping("/getTeams")
    public HashSet<Team> getTeams() {
        return teamRepo.findAll();
    }

    @PostMapping("/addTeam")
    public String addTeam(@RequestParam String team, @RequestParam String info) {
        try {
            directoriesValidator.checkTeamUniq(team);
        }catch (Exception e) {
            return e.getMessage();
        }
        teamRepo.save(new Team(team, info));
        return "Added!";
    }

    @GetMapping("/getSports")
    public HashSet<Sport> getSports() {
        return sportRepo.findAll();
    }

    @PostMapping("/addSport")
    public String addSport(@RequestParam String sport) {
        try {
            directoriesValidator.checkSportUniq(sport);
        }catch (Exception e) {
            return e.getMessage();
        }
        sportRepo.save(new Sport(sport));
        return "Added!";
    }
}
