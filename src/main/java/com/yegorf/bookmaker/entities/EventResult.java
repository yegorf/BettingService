package com.yegorf.bookmaker.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = "bets")
@JsonIgnoreProperties("bets")
public class EventResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String result;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "eventResult", cascade = CascadeType.ALL)
    private Set<Bet> bets;

}
