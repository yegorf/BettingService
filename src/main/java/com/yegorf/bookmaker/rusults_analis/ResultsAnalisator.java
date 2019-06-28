package com.yegorf.bookmaker.rusults_analis;

import com.yegorf.bookmaker.entities.Event;
import com.yegorf.bookmaker.repos.EventRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ResultsAnalisator {
    private final EventRepo eventRepo;

    public ResultsAnalisator(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    public void checkEventEnding() {
        for(Event event : eventRepo.findAllByActiveAndDateBefore(1,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))) {
            System.out.println(event.getId());
        }
    }
}
