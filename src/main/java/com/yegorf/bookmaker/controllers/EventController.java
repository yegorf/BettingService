package com.yegorf.bookmaker.controllers;

import com.yegorf.bookmaker.entities.Event;
import com.yegorf.bookmaker.repos.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    EventRepo eventRepo;

    @GetMapping("/getEvents")
    public ArrayList<Event> getEvents() {
        return (ArrayList<Event>) eventRepo.findAll();
    }
}
