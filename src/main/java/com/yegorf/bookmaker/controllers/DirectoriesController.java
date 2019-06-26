package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.entities.Bet;
import com.yegorf.bookmaker.entities.Sport;
import com.yegorf.bookmaker.entities.Team;
import com.yegorf.bookmaker.repos.BetRepo;
import com.yegorf.bookmaker.repos.SportRepo;
import com.yegorf.bookmaker.repos.TeamRepo;
import com.yegorf.bookmaker.repos.UserRepo;
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


    @GetMapping("/getTeams")
    public HashSet<Team> getTeams() {
        for(Team team : teamRepo.findAll()) {
            System.out.println("t " + team.getName());
        }
        return teamRepo.findAll();
    }

    @PostMapping("/addTeam")
    public String addTeam(@RequestParam String team, @RequestParam String info) {
        try {
            new DirectoriesValidator(sportRepo, teamRepo).checkTeamUniq(team);
        }catch (Exception e) {
            return e.getMessage();
        }
        teamRepo.save(new Team(team, info));
        return "Added!";
    }

    @PostMapping("/removeTeam")
    public void removeTeam(@RequestParam int team) {
        teamRepo.deleteById(team);
    }

    @GetMapping("/getSports")
    public HashSet<Sport> getSports() {
        return sportRepo.findAll();
    }

    @PostMapping("/addSport")
    public String addSport(@RequestParam String sport) {
        try {
            new DirectoriesValidator(sportRepo, teamRepo).checkSportUniq(sport);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        sportRepo.save(new Sport(sport));
        return "Added!";
    }

    @PostMapping("/removeSport")
    public void removeSport(@RequestParam int sport) {
        System.out.println("id " + sport);
        sportRepo.deleteById(sport);
    }
}
