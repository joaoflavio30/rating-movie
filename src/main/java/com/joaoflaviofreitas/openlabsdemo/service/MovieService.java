package com.joaoflaviofreitas.openlabsdemo.service;

import com.joaoflaviofreitas.openlabsdemo.model.Movie;
import com.joaoflaviofreitas.openlabsdemo.model.MovieDto;

import java.util.List;

public interface MovieService {

    void insertMovie(MovieDto movieDto);

    List<Movie> getMovies();

    Movie  getMovieById(Integer id);

    void updateMovie(MovieDto newMovie, Integer id);

    void deleteMovie(Integer id);
}
