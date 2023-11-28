package com.joaoflaviofreitas.openlabsdemo.service;

import com.joaoflaviofreitas.openlabsdemo.model.Movie;
import com.joaoflaviofreitas.openlabsdemo.model.MovieDto;

import java.util.List;

public interface MovieService {

    Movie insertMovie(Movie movie);

    List<Movie> getMovies();

    Movie  getMovieById(Integer id);

    Movie updateMovie(MovieDto newMovie, Integer id);

    void deleteMovie(Integer id);
}
