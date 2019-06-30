package com.yegorf.bookmaker.rusults_analis;

import com.yegorf.bookmaker.entities.Event;
import com.yegorf.bookmaker.repos.EventRepo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;


public class EventsTimeAnalyzer {
    private final EventRepo eventRepo;

    public EventsTimeAnalyzer(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    public HashSet<Event> checkEventEnding() throws ParseException {
        HashSet<Event> events = new HashSet<>();
        for (Event event : eventRepo.findAllByActive(1)) {
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            if (format.parse(event.getDate()).before(format.parse(now))) {
                events.add(event);
            }
        }
        return events;
    }
}
