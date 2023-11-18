package com.joaoflaviofreitas.openlabsdemo.service;

import com.joaoflaviofreitas.openlabsdemo.model.Movie;

import java.util.List;

public interface MovieService {

    Movie insertMovie(Movie movie);

    List<Movie> getMovies();

    Movie getMovieById(Integer id);

    Movie updateMovie(Movie movie);

    void deleteMovie(Integer id);
}
