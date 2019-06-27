package com.yegorf.bookmaker.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude = "events")
@JsonIgnoreProperties("events")
@EqualsAndHashCode(of = "id")
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String sport;

    @OneToMany(mappedBy = "sport", cascade = CascadeType.ALL)
    private Set<Event> events;

    public Sport(String sport) {
        this.sport = sport;
    }
}
