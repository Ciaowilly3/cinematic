package com.cinematic.cinematic.services;

import com.cinematic.cinematic.models.Film;

import java.util.List;

public interface FilmService {

    public List<Film> retrieveAllFilms();

    public  Film retrieveFilmById(Long id);
    public List<Film> retrieveFilmsByTitle(String title);

    public void makeNewFilm(Film film);

}
