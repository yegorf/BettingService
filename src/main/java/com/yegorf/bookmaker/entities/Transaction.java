package com.yegorf.bookmaker.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private Float sum;
    private String date;
    private String time;

    public Transaction(User user, Float sum, String date, String time) {
        this.user = user;
        this.sum = sum;
        this.date = date;
        this.time = time;
    }
}
