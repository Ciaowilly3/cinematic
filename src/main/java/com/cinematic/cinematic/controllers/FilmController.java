package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.FilmDto;
import com.cinematic.cinematic.mappers.FilmMapper;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.services.impl.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("films")
@RestController
public class FilmController {

    private final FilmServiceImpl filmService;

    @Autowired
    public FilmController(FilmServiceImpl filmService){this.filmService = filmService;}

    @GetMapping
    public List<FilmDto> retrieveAllFilms(){return FilmMapper.INSTANCE.filmsToFilmsDtos(filmService.retrieveAllFilms());}

    @GetMapping(path = "/singleFilm/{id}")
    public FilmDto retrieveFilmById(@PathVariable("id") Long id){return FilmMapper.INSTANCE.filmToFilmDto(filmService.retrieveFilmById(id));}

    @PostMapping
    public void makeNewFilm(@RequestBody Film film){filmService.makeNewFilm(film);}

    @GetMapping(path = "/{title}")
    public List<FilmDto> retrieveFilmsByTitle(@PathVariable("title") String title){return FilmMapper.INSTANCE.filmsToFilmsDtos(filmService.retrieveFilmsByTitle(title));}
}
// TODO: levare api dal path
// TODO: controllare la dependency injection venga fatta tramite requiredargsconstructor di lombok
// TODO: sostituire l'istanza statica di mapstruct con la versione Bean, occhio ad aggiornare l'annotation aggiungendo modelspring
// TODO: quando la variabile java è uguale a quella tra le graffe non specificare in pathvariable
// TODO: scrivere path in snakecase;