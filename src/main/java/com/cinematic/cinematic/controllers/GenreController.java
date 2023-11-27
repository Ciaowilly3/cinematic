package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.GenreDto;
import com.cinematic.cinematic.mappers.GenreMapper;
import com.cinematic.cinematic.models.Genre;
import com.cinematic.cinematic.services.impl.GenreServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("genres")
@RequiredArgsConstructor
@RestController
public class GenreController {

    private final GenreServiceImpl genreService;
    private final GenreMapper genreMapper;


    @GetMapping(path = "single-genre/{id}")
    public GenreDto retrieveGenreById(@PathVariable Long id){
        return genreMapper.toGenreDto(genreService.retrieveGenreById(id));
    }

    @PostMapping
    public ResponseEntity<GenreDto> makeGenre(@RequestBody Genre genre){
        return ResponseEntity.status(HttpStatus.CREATED).body(genreMapper.toGenreDto(genreService.makeGenre(genre)));
    }
}
//TODO: rendere path uguale al nome del controller SEMI-FATTO problema plurale