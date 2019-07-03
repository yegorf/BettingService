package com.yegorf.bookmaker.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(exclude = "bets")
@JsonIgnoreProperties({"bets", "event"})
public class EventResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String result;
    private int won;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event event;

    public EventResult(String result, Event event) {
        this.result = result;
        this.event = event;
    }

    @OneToMany(mappedBy = "eventResult", cascade = CascadeType.ALL)
    private Set<Bet> bets;
}
