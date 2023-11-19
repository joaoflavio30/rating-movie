package com.joaoflaviofreitas.openlabsdemo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity(name = "movie")
@Table(name = "movie")
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double rating;

}
