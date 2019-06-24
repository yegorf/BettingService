package com.yegorf.bookmaker.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_result_id")
    private EventResult eventResult;

    private Float sum;
    private Float coef;
}
