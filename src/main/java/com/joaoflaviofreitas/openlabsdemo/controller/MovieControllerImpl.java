package com.joaoflaviofreitas.openlabsdemo.controller;

import com.joaoflaviofreitas.openlabsdemo.model.Movie;
import com.joaoflaviofreitas.openlabsdemo.model.MovieDto;
import com.joaoflaviofreitas.openlabsdemo.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieControllerImpl implements MovieController {

    private final MovieService movieService;

    public MovieControllerImpl(MovieService movieService){
        this.movieService = movieService;
    }


    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insertMovie(@Valid @RequestBody MovieDto movieDto) {
        movieService.insertMovie(movieDto);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovies());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @Override
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateMovie(@Valid @RequestBody MovieDto newMovie,@PathVariable("id") Integer id) {
        movieService.updateMovie(newMovie, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") Integer id){
        movieService.deleteMovie(id);
    }
}
