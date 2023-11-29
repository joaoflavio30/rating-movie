package com.joaoflaviofreitas.openlabsdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaoflaviofreitas.openlabsdemo.exception.MovieNotFoundException;
import com.joaoflaviofreitas.openlabsdemo.model.Movie;
import com.joaoflaviofreitas.openlabsdemo.model.MovieDto;
import com.joaoflaviofreitas.openlabsdemo.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    public void whenInsertMovie_shouldReturnMovie() throws Exception{
        MovieDto movieDto = new MovieDto("Spider-Man", 10.0);

        doNothing().when(movieService).insertMovie(movieDto);

        mockMvc.perform(post("/movies").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(movieDto)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void whenGetMovies_shouldReturnMovies() throws Exception {
        Movie movie1 = new Movie(1,"Spider-Man",10.0);
        Movie movie2 = new Movie(2,"Spider-Man 2",8.0);
        Movie movie3 = new Movie(3,"Spider-Man 3",9.0);
        List<Movie> myList = new ArrayList<>();
        myList.add(movie1);
        myList.add(movie2);
        myList.add(movie3);
        when(movieService.getMovies()).thenReturn(myList);

        List<Movie> listMovies = movieService.getMovies();

        mockMvc.perform(get("/movies").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(listMovies)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(myList.get(0).getId()))
                .andExpect(jsonPath("$.[0].name").value(myList.get(0).getName()))
                .andExpect(jsonPath("$.[0].rating").value(myList.get(0).getRating()))
                .andExpect(jsonPath("$.[1].id").value(myList.get(1).getId()))
                .andExpect(jsonPath("$.[1].name").value(myList.get(1).getName()))
                .andExpect(jsonPath("$.[1].rating").value(myList.get(1).getRating()))
                .andExpect(jsonPath("$.[2].id").value(myList.get(2).getId()))
                .andExpect(jsonPath("$.[2].name").value(myList.get(2).getName()))
                .andExpect(jsonPath("$.[2].rating").value(myList.get(2).getRating()))
                .andDo(print());
    }

    @Test
    void whenGetMovieById_shouldReturnMovie() throws Exception {
        Movie movie = new Movie(1,"Spider-Man",10.0);

        when(movieService.getMovieById(movie.getId())).thenReturn(movie);

        mockMvc.perform(get("/movies/{id}",movie.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(movie.getId()))
                .andExpect(jsonPath("$.name").value(movie.getName()))
                .andExpect(jsonPath("$.rating").value(movie.getRating()))
                .andDo(print());
    }

    @Test
    void whenGetMovieById_shouldReturnException() throws Exception{
        Movie movie = new Movie(1,"Spider-Man",10.0);

        when(movieService.getMovieById(movie.getId())).thenThrow(MovieNotFoundException.class);

        mockMvc.perform(get("/movies/{id}",movie.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void whenUpdateMovie_shouldReturnOkHttpStatus() throws Exception{
        Movie movie = new Movie(1,"Spider-Man",10.0);
        MovieDto movieDto = new MovieDto("Spider-Man 2",8.0);

        doNothing().when(movieService).updateMovie(movieDto, movie.getId());


        mockMvc.perform(put("/movies/{id}",movie.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(movieDto)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void whenUpdateMovie_shouldReturnException() throws Exception {
        Movie movie = new Movie(1, "Spider-Man 2",8.0);
        MovieDto movieDto = new MovieDto("Spider-Man 2",9.0);
        Movie newMovie = new Movie(movie.getId(), "Spider-Man 2",9.0);

        doThrow(MovieNotFoundException.class).when(movieService).updateMovie(movieDto, movie.getId());

        mockMvc.perform(put("/movies/{id}",movie.getId()).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(newMovie)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void whenDeleteMovie_shouldReturnVoid() throws Exception{
        Movie movie = new Movie(1,"Spider-Man",10.0);

        doNothing().when(movieService).deleteMovie(movie.getId());

        mockMvc.perform(delete("/movies/{id}",movie.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void whenDeleteMovie_shouldReturnException() throws Exception {
       doThrow(MovieNotFoundException.class).when(movieService).deleteMovie(1);

        mockMvc.perform(delete("/movies/{id}",1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}