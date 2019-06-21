package com.yegorf.bookmaker.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Team(String name, String info) {
        this.name = name;
        this.info = info;
    }

    private String name;
    private String info;
}
