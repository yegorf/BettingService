package com.yegorf.bookmaker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(of="id")
@Entity
@NoArgsConstructor
@JsonIgnoreProperties("bets")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String password;
    private String email;
    private Integer admin = 0;
    private Float balance;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Bet> bets;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
