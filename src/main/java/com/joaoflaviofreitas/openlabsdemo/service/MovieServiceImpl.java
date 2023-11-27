package com.joaoflaviofreitas.openlabsdemo.service;

import com.joaoflaviofreitas.openlabsdemo.data.MovieRepository;
import com.joaoflaviofreitas.openlabsdemo.exception.MovieNotFoundException;
import com.joaoflaviofreitas.openlabsdemo.model.Movie;
import com.joaoflaviofreitas.openlabsdemo.model.MovieDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie insertMovie(Movie movie) {
        return movieRepository.save(movie);
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
    public Movie updateMovie(MovieDto newMovie, Integer id) {
        Movie originalMovie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with the given ID."));
        BeanUtils.copyProperties(newMovie, originalMovie);
        movieRepository.save(originalMovie);
        return originalMovie;
    }

    @Override
    public void deleteMovie(Integer id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found with the given ID."));
        movieRepository.deleteById(movie.getId());
    }
}
