package com.joaoflaviofreitas.openlabsdemo.controller;

import com.joaoflaviofreitas.openlabsdemo.model.Movie;
import com.joaoflaviofreitas.openlabsdemo.model.MovieDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface MovieController {

    ResponseEntity<Void> insertMovie(@Valid MovieDto movieDto);

    ResponseEntity<List<Movie>> getMovies();

    ResponseEntity<Movie> getMovieById(Integer id);

    ResponseEntity<Void> updateMovie(@Valid MovieDto newMovie, Integer id);

    void deleteMovie(Integer id);
}
