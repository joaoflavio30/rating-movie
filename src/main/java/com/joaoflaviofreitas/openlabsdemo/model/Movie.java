package com.joaoflaviofreitas.openlabsdemo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity(name = "movie")
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double rating;

    public Movie(String name, Double rating) {
        this.name = name;
        this.rating = rating;
    }
}
