package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.FilmDto;
import com.cinematic.cinematic.mappers.FilmMapper;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.services.impl.FilmServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RequestMapping("films")
@RequiredArgsConstructor
@RestController
@Tag(name = "controller for films", description = "makes a bunch of staff for films")
public class FilmController {

    private final FilmServiceImpl filmService;
    private final FilmMapper filmMapper;

    @Operation(summary = "Get a list of films")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list of films",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Film.class)) }),
            @ApiResponse(responseCode = "404", description = "Film list not found",
                    content = @Content) })
    @GetMapping
    public List<FilmDto> retrieveAllFilms(){return filmMapper.toFilmsDtos(filmService.retrieveAllFilms());}

    @Operation(summary = "Get a film by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the film",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Film.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Film not found",
                    content = @Content) })
    @GetMapping(path = "/single-film/{id}")
    public FilmDto retrieveFilmById(@PathVariable Long id){return filmMapper.toFilmDto(filmService.retrieveFilmById(id));}

    @Operation(summary = "Make a film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Film made",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Film.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid film supplied",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<FilmDto> makeNewFilm(@RequestBody Film film){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(filmMapper.toFilmDto(filmService.makeNewFilm(film)));
    }

    @Operation(summary = "Get a film list by its title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the film list",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Film.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid title supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Film not found",
                    content = @Content) })
    @GetMapping(path = "/{title}")
    public List<FilmDto> retrieveFilmsByTitle(@PathVariable String title){return filmMapper.toFilmsDtos(filmService.retrieveFilmsByTitle(title));}

    @Operation(summary = "Update a film by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated the film",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Film.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Film not found",
                    content = @Content) })
    @PutMapping(path = "/update-film/{id}")
    public ResponseEntity<FilmDto> updateFilm(@PathVariable Long id, @RequestBody Film film){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(filmMapper.toFilmDto(filmService.updateFilm(film, id)));
    }

    @Operation(summary = "Delete a film by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deleted the film",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Film.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Film not found",
                    content = @Content) })
    @DeleteMapping(path = "/delete-film/{id}")
    public ResponseEntity<FilmDto> removeFilm(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(filmMapper.toFilmDto(filmService.removeFilm(id)));
    }
}
// TODO: levare api dal path FATTO
// TODO: controllare la dependency injection venga fatta tramite requiredargsconstructor di lombok FATTO
// TODO: sostituire l'istanza statica di mapstruct con la versione Bean, occhio ad aggiornare l'annotation aggiungendo modelspring FATTO
// TODO: quando la variabile java è uguale a quella tra le graffe non specificare in pathvariable FATTO
// TODO: scrivere path in snakecase; FATTO