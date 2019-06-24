package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.entities.Event;
import com.yegorf.bookmaker.entities.Part;
import com.yegorf.bookmaker.entities.Sport;
import com.yegorf.bookmaker.entities.Team;
import com.yegorf.bookmaker.dto.JsonEvent;
import com.yegorf.bookmaker.repos.EventRepo;
import com.yegorf.bookmaker.repos.PartRepo;
import com.yegorf.bookmaker.repos.SportRepo;
import com.yegorf.bookmaker.repos.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    EventRepo eventRepo;
    @Autowired
    SportRepo sportRepo;
    @Autowired
    TeamRepo teamRepo;
    @Autowired
    PartRepo partRepo;

    @GetMapping("/getEvents")
    public ArrayList<JsonEvent> getEvents() {
        ArrayList<JsonEvent> jsonEvents = new ArrayList<>();
        for(Event e : eventRepo.findAll()) {
            JsonEvent jsonEvent = new JsonEvent();
            jsonEvent.setSport(e.getSport().getSport());
            for(Part p : e.getParts()) {
                System.out.println("p " + p.getTeam().getName());
                if(jsonEvent.getTeam1() == null) {
                    jsonEvent.setTeam1(p.getTeam().getName());
                } else {
                    jsonEvent.setTeam2(p.getTeam().getName());
                }
            }
            jsonEvent.setDate(e.getDate());
            jsonEvents.add(jsonEvent);
        }

        return jsonEvents;
    }

    @PostMapping("/addEvent")
    public void addEvent(@RequestParam Integer sport,
                           @RequestParam Integer team1,
                           @RequestParam Integer team2,
                            @RequestParam String date
                         ) {

        Event event = new Event();
        for(Sport s : sportRepo.findAll()) {
            if(s.getId().equals(sport)) {
                event.setSport(s);
                event.setDate(date);
                eventRepo.save(event);
                break;
            }
        }
        for(Team team : teamRepo.findAll()) {
            if(team.getId().equals(team1) || team.getId().equals(team2)) {
                partRepo.save(new Part(event, team));
            }
        }
    }
}
