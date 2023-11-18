package com.joaoflaviofreitas.openlabsdemo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "movie")
@NoArgsConstructor
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
