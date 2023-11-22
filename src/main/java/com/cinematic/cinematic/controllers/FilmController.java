package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.FilmDto;
import com.cinematic.cinematic.mappers.FilmMapper;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.services.impl.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/films")
@RestController
public class FilmController {

    private final FilmServiceImpl filmService;

    @Autowired
    public FilmController(FilmServiceImpl filmService){this.filmService = filmService;}

    @GetMapping
    public List<FilmDto> retrieveAllFilms(){return FilmMapper.INSTANCE.filmsToFilmsDtos(filmService.retrieveAllFilms());}

    @GetMapping(path = "/singleFilm/{id}")
    public FilmDto retrieveFilmById(@PathVariable("id") Long id){return FilmMapper.INSTANCE.filmToFilmDto(filmService.retrieveFilmById(id));}

    @GetMapping(path = "/{title}")
    public List<FilmDto> retrieveFilmsByTitle(@PathVariable("title") String title){return FilmMapper.INSTANCE.filmsToFilmsDtos(filmService.retrieveFilmsByTitle(title));}
}
