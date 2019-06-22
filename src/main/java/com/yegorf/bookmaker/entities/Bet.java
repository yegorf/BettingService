package com.yegorf.bookmaker.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//    private Integer user_id;
//    private Integer event_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private Float sum;
    private Float coef;
}
