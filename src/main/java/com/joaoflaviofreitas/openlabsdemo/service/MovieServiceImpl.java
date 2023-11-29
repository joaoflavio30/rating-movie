package com.joaoflaviofreitas.openlabsdemo.service;

import com.joaoflaviofreitas.openlabsdemo.data.MovieRepository;
import com.joaoflaviofreitas.openlabsdemo.exception.MovieNotFoundException;
import com.joaoflaviofreitas.openlabsdemo.model.Movie;
import com.joaoflaviofreitas.openlabsdemo.model.MovieDto;
import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void insertMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setName(movieDto.name());
        movie.setRating(movieDto.rating());
        movieRepository.save(movie);
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with the given ID."));

    }

    @Override
    public void updateMovie(MovieDto newMovie, Integer id) {
        Movie originalMovie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with the given ID."));
        BeanUtils.copyProperties(newMovie, originalMovie);
        movieRepository.save(originalMovie);
    }

    @Override
    public void deleteMovie(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with the given ID."));
        movieRepository.deleteById(movie.getId());
    }
}
