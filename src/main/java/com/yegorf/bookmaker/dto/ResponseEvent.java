package com.yegorf.bookmaker.dto;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Data
public class ResponseEvent implements Comparable<ResponseEvent> {
    private Integer id;
    private String sport;
    private String team1;
    private String team2;
    private String date;
    private String active;

    @Override
    public int compareTo(ResponseEvent r) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (format.parse(this.getDate()).before(format.parse(r.getDate()))) {
                return -1;
            } else if (format.parse(this.getDate()).equals(format.parse(r.getDate()))) {
                return 0;
            } else {
                return 1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
