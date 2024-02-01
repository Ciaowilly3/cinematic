package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.GenreDto;
import com.cinematic.cinematic.mappers.GenreMapper;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.models.Genre;
import com.cinematic.cinematic.services.impl.GenreServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("genres")
@RequiredArgsConstructor
@RestController
@Tag(name = "controller for genre", description = "makes a bunch of staff for genres")
public class GenreController {

    private final GenreServiceImpl genreService;
    private final GenreMapper genreMapper;

    @Operation(summary = "Get a list of genres")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Genre.class)) }),
            @ApiResponse(responseCode = "404", description = "lsit not found",
                    content = @Content) })
    @GetMapping
    public List<GenreDto> retrieveAllGenres(){
        return genreMapper.toGenreDtos(genreService.retrieveAllGenres());
    }

    @Operation(summary = "Get a genre by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the genre",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Genre.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "genre not found",
                    content = @Content) })
    @GetMapping(path = "single-genre/{id}")
    public GenreDto retrieveGenreById(@PathVariable Long id){
        return genreMapper.toGenreDto(genreService.retrieveGenreById(id));
    }

    @Operation(summary = "Make a genre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "genre made",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Genre.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid genre supplied",
                    content = @Content)})
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/private/make-genre")
    public ResponseEntity<GenreDto> makeGenre(@RequestBody Genre genre){
        return ResponseEntity.status(HttpStatus.CREATED).body(genreMapper.toGenreDto(genreService.makeGenre(genre)));
    }
}
//TODO: rendere path uguale al nome del controller SEMI-FATTO problema plurale