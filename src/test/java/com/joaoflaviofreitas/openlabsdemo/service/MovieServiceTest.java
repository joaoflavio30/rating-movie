package com.joaoflaviofreitas.openlabsdemo.service;

import com.joaoflaviofreitas.openlabsdemo.data.MovieRepository;
import com.joaoflaviofreitas.openlabsdemo.exception.MovieNotFoundException;
import com.joaoflaviofreitas.openlabsdemo.model.Movie;
import com.joaoflaviofreitas.openlabsdemo.model.MovieDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;


class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Autowired
    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenInsertMovie_shouldCallTheSaveMethod() {
        MovieDto movieDto = new MovieDto("Spider-Man", 10.0);
        Movie movieToSave = new Movie();
        movieToSave.setName(movieDto.name());
        movieToSave.setRating(movieDto.rating());

        when(movieRepository.save(any(Movie.class))).thenReturn(movieToSave);

        movieService.insertMovie(movieDto);

        // Here i use 'times()' because i instantiate a different Movie
        verify(movieRepository, times(1)).save(any(Movie.class));
    }
    @Test
    void whenGetMovies_shouldReturnMovies(){
        Movie movie1 = new Movie(1,"Spider-Man",10.0);
        Movie movie2 = new Movie(2,"Spider-Man 2",8.0);
        Movie movie3 = new Movie(3,"Spider-Man 3",9.0);
        List<Movie> myList = new ArrayList<>();
        myList.add(movie1);
        myList.add(movie2);
        myList.add(movie3);
        when(movieRepository.findAll()).thenReturn(myList);

        List<Movie> listMovies = movieService.getMovies();

        assertThat(listMovies.size()).isSameAs(myList.size());
        assertThat(listMovies.get(0).getName()).isSameAs(myList.get(0).getName());
        verify(movieRepository).findAll();
    }

    @Test
    void whenGetMovieById_shouldReturnMovie(){
        Movie movie = new Movie(1,"Spider-Man",10.0);

        when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));

        Movie foundMovie = movieService.getMovieById(movie.getId());

        assertThat(foundMovie).isSameAs(movie);
        assertThat(foundMovie.getId()).isSameAs(movie.getId());
        verify(movieRepository).findById(movie.getId());
    }

    @Test
    void whenGetMovieById_shouldReturnException() throws MovieNotFoundException {
        when(movieRepository.findById(2)).thenReturn(Optional.empty());

        MovieNotFoundException thrown = assertThrows(MovieNotFoundException.class, () -> movieService.getMovieById(2));

        assertEquals("Movie not found with the given ID.", thrown.getMessage());
        verify(movieRepository).findById(2);
    }

    @Test
    void whenUpdateMovie_shouldCallTheSaveMethod(){
        Movie originalMovie = new Movie(1,"Spider-Man",10.0);
        MovieDto movieDto = new MovieDto("Spider-Man 2",8.0);

        when(movieRepository.findById(originalMovie.getId())).thenReturn(Optional.of(originalMovie));
        when(movieRepository.save(any())).thenReturn(originalMovie);

        movieService.updateMovie(movieDto, originalMovie.getId());

        verify(movieRepository).findById(originalMovie.getId());
        verify(movieRepository).save(originalMovie);
        assertEquals(originalMovie.getRating(),movieDto.rating());
    }

    @Test
    void whenUpdateMovie_shouldReturnException() throws MovieNotFoundException {
        MovieDto movieDto = new MovieDto("Spider-Man 2",8.0);

        when(movieRepository.findById(1)).thenReturn(Optional.empty());
        when(movieRepository.save(any())).thenReturn(any());


        MovieNotFoundException thrown = assertThrows(MovieNotFoundException.class, () -> movieService.updateMovie(movieDto,1));

        assertEquals("Movie not found with the given ID.", thrown.getMessage());
        verify(movieRepository).findById(1);
    }

    @Test
    void whenDeleteMovie_shouldReturnTheDeleteMethod(){
        Movie movie = new Movie(1,"Spider-Man",10.0);

        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        doNothing().when(movieRepository).deleteById(1);

        movieService.deleteMovie(1);

        verify(movieRepository).deleteById(1);
    }

    @Test
    void whenDeleteMovie_shouldReturnException() throws MovieNotFoundException {
        when(movieRepository.findById(1)).thenReturn(Optional.empty());
        doNothing().when(movieRepository).deleteById(1);

        MovieNotFoundException thrown = assertThrows(MovieNotFoundException.class, () -> movieService.deleteMovie(1));

        assertEquals("Movie not found with the given ID.", thrown.getMessage());
        // there is no 'verify' because before deleteById is called the exception is thrown
    }
}