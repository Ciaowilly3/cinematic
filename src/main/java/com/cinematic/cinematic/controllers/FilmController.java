package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.FilmDto;
import com.cinematic.cinematic.mappers.FilmMapper;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.services.impl.FilmServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("films")
@RequiredArgsConstructor
@RestController
public class FilmController {

    private final FilmServiceImpl filmService;
    private final FilmMapper filmMapper;

    @GetMapping
    public List<FilmDto> retrieveAllFilms(){return filmMapper.filmsToFilmsDtos(filmService.retrieveAllFilms());}

    @GetMapping(path = "/singleFilm/{id}")
    public FilmDto retrieveFilmById(@PathVariable Long id){return filmMapper.filmToFilmDto(filmService.retrieveFilmById(id));}

    @PostMapping
    public void makeNewFilm(@RequestBody Film film){filmService.makeNewFilm(film);}

    @GetMapping(path = "/{title}")
    public List<FilmDto> retrieveFilmsByTitle(@PathVariable String title){return filmMapper.filmsToFilmsDtos(filmService.retrieveFilmsByTitle(title));}
}
// TODO: levare api dal path FATTO
// TODO: controllare la dependency injection venga fatta tramite requiredargsconstructor di lombok FATTO
// TODO: sostituire l'istanza statica di mapstruct con la versione Bean, occhio ad aggiornare l'annotation aggiungendo modelspring FATTO
// TODO: quando la variabile java è uguale a quella tra le graffe non specificare in pathvariable FATTO
// TODO: scrivere path in snakecase;