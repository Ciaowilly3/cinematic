package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.GenreDto;
import com.cinematic.cinematic.mappers.GenreMapper;
import com.cinematic.cinematic.repositories.GenreRepository;
import com.cinematic.cinematic.services.impl.GenreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/genres")
@RestController
public class GenreController {

    private final GenreServiceImpl genreService;

    @Autowired
    public GenreController(GenreServiceImpl genreService){this.genreService = genreService;}

    @GetMapping(path = "singleGenre/{id}")
    public GenreDto retrieveGenreById(@PathVariable("id")Long id){
        return GenreMapper.INSTANCE.genreToGenreDto(genreService.retrieveGenreById(id));
    }
}
