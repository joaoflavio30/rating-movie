package com.joaoflaviofreitas.openlabsdemo.controller;

import com.joaoflaviofreitas.openlabsdemo.model.Movie;
import com.joaoflaviofreitas.openlabsdemo.model.MovieDto;
import com.joaoflaviofreitas.openlabsdemo.service.MovieService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieControllerImpl implements MovieController {

    @Autowired
    private MovieService movieService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Movie> insertMovie(@RequestBody Movie movie) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.insertMovie(movie));
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
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@Valid @RequestBody MovieDto newMovie,@PathVariable("id") Integer id) {
        Movie updatedMovie = movieService.updateMovie(newMovie, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedMovie);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable("id") Integer id){
        movieService.deleteMovie(id);
    }
}
