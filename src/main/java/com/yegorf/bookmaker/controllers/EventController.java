package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.dto.JsonEvent;
import com.yegorf.bookmaker.entities.*;
import com.yegorf.bookmaker.repos.*;
import com.yegorf.bookmaker.rusults_analis.EventsTimeAnalyzer;
import com.yegorf.bookmaker.rusults_analis.WinningsPayer;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventRepo eventRepo;
    private final SportRepo sportRepo;
    private final TeamRepo teamRepo;
    private final PartRepo partRepo;
    private final EventResultRepo eventResultRepo;

    public EventController(EventRepo eventRepo, SportRepo sportRepo, TeamRepo teamRepo, PartRepo partRepo, EventResultRepo eventResultRepo) {
        this.eventRepo = eventRepo;
        this.sportRepo = sportRepo;
        this.teamRepo = teamRepo;
        this.partRepo = partRepo;
        this.eventResultRepo = eventResultRepo;
    }

    @GetMapping("/getEvents")
    public ArrayList<JsonEvent> getEvents() {
        return convertEvents(eventRepo.findAll());
    }

    @PostMapping("/addEvent")
    public void addEvent(@RequestParam int sport,
                         @RequestParam int team1,
                         @RequestParam int team2,
                         @RequestParam String date,
                         @RequestParam int profit
    ) {

        Event event = new Event();
        event.setProfit(profit);
        event.setActive(1);
        for (Sport s : sportRepo.findAll()) {
            if (s.getId().equals(sport)) {
                event.setSport(s);
                event.setDate(date);
                eventRepo.save(event);
                break;
            }
        }
        for (Team team : teamRepo.findAll()) {
            if (team.getId().equals(team1) || team.getId().equals(team2)) {
                partRepo.save(new Part(event, team));
            }
        }

        eventResultRepo.save(new EventResult("1 team win", event));
        eventResultRepo.save(new EventResult("2 team win", event));
    }

    //TEST EXAMPLE
    @PostMapping("/getEvent")
    public JsonEvent getEvent(@RequestParam int id) {
        ArrayList<JsonEvent> jsonEvents = convertEvents(eventRepo.findAll());
        for (JsonEvent event : jsonEvents) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }

    @GetMapping("/getPastEvents")
    public ArrayList<JsonEvent> getPastEvents() {
        EventsTimeAnalyzer analyzer = new EventsTimeAnalyzer(eventRepo);
        ArrayList<JsonEvent> events = null;
        try {
            events = convertEvents(analyzer.checkEventEnding());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return events;
    }

    //TEST EXAMPLE
    private ArrayList<JsonEvent> convertEvents(HashSet<Event> events) {
        ArrayList<JsonEvent> jsonEvents = new ArrayList<>();
        for (Event e : events) {
            JsonEvent jsonEvent = new JsonEvent();
            jsonEvent.setSport(e.getSport().getSport());
            for (Part p : e.getParts()) {
                if (jsonEvent.getTeam1() == null) {
                    jsonEvent.setTeam1(p.getTeam().getName());
                } else {
                    jsonEvent.setTeam2(p.getTeam().getName());
                }
            }
            jsonEvent.setDate(e.getDate());
            jsonEvent.setId(e.getId());
            jsonEvent.setActive(e.getActive());
            jsonEvents.add(jsonEvent);
        }
        return jsonEvents;
    }
}
