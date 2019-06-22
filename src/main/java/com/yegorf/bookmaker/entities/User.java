package com.yegorf.bookmaker.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(of="id")
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Bet> bets;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return id + " ";
    }
}
