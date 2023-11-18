package com.joaoflaviofreitas.openlabsdemo.controller;

import com.joaoflaviofreitas.openlabsdemo.model.Movie;
import com.joaoflaviofreitas.openlabsdemo.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public Movie insertMovie(@RequestBody Movie movie) {
        return movieService.insertMovie(movie);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable("id") Integer id) {
        return movieService.getMovieById(id);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieService.updateMovie(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") Integer id) {
        movieService.deleteMovie(id);
    }
}
