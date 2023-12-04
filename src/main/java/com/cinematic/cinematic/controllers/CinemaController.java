package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.CinemaDto;
import com.cinematic.cinematic.mappers.CinemaMapper;
import com.cinematic.cinematic.models.Cinema;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.services.impl.CinemaServiceImpl;
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

@RequestMapping("cinema")
@RequiredArgsConstructor
@RestController
@Tag(name = "controller for cinema", description = "makes a bunch of staff for cinema")
public class CinemaController {

    private final CinemaServiceImpl cinemaService;
    private final CinemaMapper cinemaMapper;

    @Operation(summary = "Get a list of cinema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list of cinema",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cinema.class)) }),
            @ApiResponse(responseCode = "404", description = "cinema not found",
                    content = @Content) })
    @GetMapping
    public List<CinemaDto> retrieveAllCinema(){
        return cinemaMapper.toCinemaDtos(cinemaService.retrieveAllCinema());
    }

    @Operation(summary = "Make a Cinema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "cinema made",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cinema.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid cinema supplied",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<CinemaDto> addCinema(@RequestBody Cinema cinema){return ResponseEntity.status(HttpStatus.CREATED).body(cinemaMapper.toCinemaDto(cinemaService.addCinema(cinema)));}
}
