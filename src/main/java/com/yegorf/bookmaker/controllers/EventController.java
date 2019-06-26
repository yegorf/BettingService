package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.entities.*;
import com.yegorf.bookmaker.dto.JsonEvent;
import com.yegorf.bookmaker.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

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
    @Autowired
    EventResultRepo eventResultRepo;

    @GetMapping("/getEvents")
    public ArrayList<JsonEvent> getEvents() {
        ArrayList<JsonEvent> jsonEvents = new ArrayList<>();
        for(Event e : eventRepo.findAll()) {
            JsonEvent jsonEvent = new JsonEvent();
            jsonEvent.setSport(e.getSport().getSport());
            for(Part p : e.getParts()) {
                if(jsonEvent.getTeam1() == null) {
                    jsonEvent.setTeam1(p.getTeam().getName());
                } else {
                    jsonEvent.setTeam2(p.getTeam().getName());
                }
            }
            jsonEvent.setDate(e.getDate());
            jsonEvent.setId(e.getId());
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

        eventResultRepo.save(new EventResult("1 team win", event));
        eventResultRepo.save(new EventResult("2 team win", event));
    }

    //TEST EXAMPLE
    @PostMapping("/getEvent")
    public JsonEvent getEvent(@RequestParam int id) {
        ArrayList<JsonEvent> jsonEvents = new ArrayList<>();
        for(Event e : eventRepo.findAll()) {
            JsonEvent jsonEvent = new JsonEvent();
            jsonEvent.setSport(e.getSport().getSport());
            for(Part p : e.getParts()) {
                if(jsonEvent.getTeam1() == null) {
                    jsonEvent.setTeam1(p.getTeam().getName());
                } else {
                    jsonEvent.setTeam2(p.getTeam().getName());
                }
            }
            jsonEvent.setDate(e.getDate());
            jsonEvent.setId(e.getId());
            jsonEvents.add(jsonEvent);
        }
        for (JsonEvent event : jsonEvents) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }
}
