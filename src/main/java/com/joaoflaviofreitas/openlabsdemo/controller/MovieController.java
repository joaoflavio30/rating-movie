package com.joaoflaviofreitas.openlabsdemo.controller;

import com.joaoflaviofreitas.openlabsdemo.model.Movie;
import com.joaoflaviofreitas.openlabsdemo.model.MovieDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieController {

    public ResponseEntity<Movie> insertMovie(Movie movie);

    public ResponseEntity<List<Movie>> getMovies();

    public ResponseEntity<Movie> getMovieById(Integer id);

    public ResponseEntity<Movie> updateMovie(MovieDto newMovie, Integer id);

    public void deleteMovie(Integer id);
}
