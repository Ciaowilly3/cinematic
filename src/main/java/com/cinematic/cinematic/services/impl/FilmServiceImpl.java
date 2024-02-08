package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.exceptions.NotFoundException;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.models.FilmsGenres;
import com.cinematic.cinematic.repositories.FilmRepository;
import com.cinematic.cinematic.repositories.FilmsGenresRepository;
import com.cinematic.cinematic.repositories.GenreRepository;
import com.cinematic.cinematic.services.FilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    private final FilmsGenresServiceImpl filmsGenresService;

    public List<Film> retrieveAllFilms(){
        log.info("Start - retrieveAllFilms - args:none");
        val allFilms = filmRepository.findAll();
        log.info("End - retrieveAllFilms - out: {}", allFilms.size());
        return allFilms;
    }

    public Film retrieveFilmById(Long id){
        log.info("Start - retrieveFilmsById - args: id: {}", id);
        val film = filmRepository.findById(id);
        log.info("End - retrieveFilmsById - out: {}", film.orElse(null));
        return film.orElseThrow(() -> new NotFoundException("no film found with id " + id));
    }
    public List<Film> retrieveFilmsByTitle(String title){
        log.info("Start - retrieveFilmsByTitle - args: title: {}", title);
        val films = filmRepository.findAllByTitleContaining(title);
        log.info("End - retrieveFilmsByTitle - out: {}", films.size());
        return films;
    }

    public Film makeNewFilm(Film film){
        log.info("Start - makeNewFilm - args: film: {}", film);
        filmRepository.save(film);
        if (film.getFilmGenre() != null){
            for (FilmsGenres filmsGenres : film.getFilmGenre()){
                String string =  filmsGenres.getGenre().getGenreName();
               filmsGenresService.addGenreToFilm(film.getTitle(), string);
            }
        }
        log.info("End - makeNewFilm - out: {}", film);
        return film;
    }

    public Film updateFilm(Film film, Long idToUpdate){
        log.info("Start - updateFilm - args: Film: {}", film);
        val filmToUpdate = filmRepository.findById(idToUpdate).orElseThrow(() -> new NotFoundException("Film with id " + idToUpdate + " Not Found"));
        film.setFilmId(idToUpdate);
        if (film.getFilmGenre() != null){
            filmsGenresService.deleteRelation(idToUpdate);
            for (FilmsGenres filmsGenres : film.getFilmGenre()){
                String string =  filmsGenres.getGenre().getGenreName();
                filmsGenresService.addGenreToFilm(film.getTitle(), string);
            }
        }
        BeanUtils.copyProperties(film, filmToUpdate);
        filmRepository.save(filmToUpdate);
        log.info("End - updateFilm - out: {}", filmToUpdate);
        return filmToUpdate;
    }

    public Film removeFilm(Long idToDelete){
        log.info("Start - deleteFilm - args: id: {}", idToDelete);
        filmsGenresService.deleteRelation(idToDelete);
        val filmToDelete = filmRepository.findById(idToDelete).orElseThrow(() -> new NotFoundException("Film with id " + idToDelete + " Not Found"));
        filmRepository.delete(filmToDelete);
        log.info("End - deleteFilm - args: film: {}", filmToDelete);
        return filmToDelete;
    }

}
