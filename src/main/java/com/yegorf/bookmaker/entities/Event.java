package com.yegorf.bookmaker.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "parts")
@JsonIgnoreProperties({"parts", "results"})
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<Part> parts;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private Set<EventResult> results;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sport_id")
    private Sport sport;

    private String date;
    private String active;
    private int profit;

    public Event(Sport sport, String date) {
        this.sport = sport;
        this.date = date;
    }
}
