package com.joaoflaviofreitas.openlabsdemo.controller;

import com.joaoflaviofreitas.openlabsdemo.model.Movie;
import com.joaoflaviofreitas.openlabsdemo.model.MovieDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface MovieController {

    @Operation(summary = "Insira o nome do filme e sua nota.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nota de filme criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de requisição invalida", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    ResponseEntity<Void> insertMovie(@Valid MovieDto movieDto);

    @Operation(summary = "Obtenha todos os filmes avaliados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nota de filmes obtidas com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    ResponseEntity<List<Movie>> getMovies();

    @Operation(summary = "Obtenha o filme avaliado por id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nota de filme criada com sucesso"),
            @ApiResponse(responseCode = "200", description = "Nota de filmes obtidas com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    ResponseEntity<Movie> getMovieById(Integer id);

    @Operation(summary = "Atualize o filme por Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nota de filme criada com sucesso"),
            @ApiResponse(responseCode = "200", description = "Nota de filmes obtidas com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    ResponseEntity<Void> updateMovie(@Valid MovieDto newMovie, Integer id);

    @Operation(summary = "Delete o filme avaliado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nota de filme criada com sucesso"),
            @ApiResponse(responseCode = "200", description = "Nota de filmes obtidas com sucesso"),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content)
    })
    void deleteMovie(Integer id);
}
