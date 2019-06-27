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
@JsonIgnoreProperties("parts")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private Set<Part> parts;

    public Team(String name, String info) {
        this.name = name;
        this.info = info;
    }

    private String name;
    private String info;
}
