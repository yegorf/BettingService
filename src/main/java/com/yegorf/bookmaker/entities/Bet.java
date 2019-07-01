package com.yegorf.bookmaker.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@JsonIgnoreProperties({"eventResult", "user"})
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
    private String status;

    public Bet(User user, EventResult eventResult, Float sum, Float coef) {
        this.user = user;
        this.eventResult = eventResult;
        this.sum = sum;
        this.coef = coef;
    }
}
