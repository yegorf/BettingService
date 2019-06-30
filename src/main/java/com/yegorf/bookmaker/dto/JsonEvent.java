package com.yegorf.bookmaker.dto;

import lombok.Data;

@Data
public class JsonEvent {
    private Integer id;
    private String sport;
    private String team1;
    private String team2;
    private String date;
    private int active;
}
