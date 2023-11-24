package com.cinematic.cinematic.services;

import com.cinematic.cinematic.models.Film;

import java.util.List;

public interface FilmService {

    List<Film> retrieveAllFilms();

    Film retrieveFilmById(Long id);
    List<Film> retrieveFilmsByTitle(String title);

    void makeNewFilm(Film film);

}
