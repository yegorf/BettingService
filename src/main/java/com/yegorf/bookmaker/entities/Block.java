package com.yegorf.bookmaker.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int term;
    private String date;
    private String reason;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Block(User user, String date, int term, String reason) {
        this.term = term;
        this.date = date;
        this.reason = reason;
        this.user = user;
    }
}
