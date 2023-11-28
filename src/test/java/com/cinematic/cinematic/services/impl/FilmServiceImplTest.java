package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.repositories.FilmRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmServiceImplTest {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmServiceImpl filmService;
    @Test
    void retrieveAllFilms() {
        val film1 = Film.builder().title("trappola di cristallo").build();
        val film2 = Film.builder().title("Rocky").build();
        val filmList = List.of(film1, film2);
        when(filmRepository.findAll()).thenReturn(filmList);

        val result = filmService.retrieveAllFilms();

        assertEquals(filmList, result);
        verify(filmRepository, times(1)).findAll();
    }

    @Test
    void retrieveFilmsByTitle() {
        val film = Film.builder().title("Rocky").build();
        when(filmRepository.findAllByTitleContaining("Ro")).thenReturn(List.of(film));

        val result = filmService.retrieveFilmsByTitle("Ro");

        assertEquals(film, result.get(0));
    }

    @Test
    void retrieveFilmById() {
        val filmId = 12L;
        val film = Film.builder().title("Rocky").filmId(filmId).build();

        when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));

        val result = filmService.retrieveFilmById(filmId);

        assertEquals(film, result);

    }

    @Test
    void makeNewFilm() {
        val film = Film.builder().title("Rocky").build();

        filmService.makeNewFilm(film);

        verify(filmRepository, times(1)).save(film);
    }

    @Test
    void updateFilm() {
        val filmId = 12L;

        val filmToUpdate = Film.builder().title("trappola di cristallo").filmId(filmId).build();
        val newFilm = Film.builder().title("Rocky").filmId(filmId).build();

        when(filmRepository.findById(filmId)).thenReturn(Optional.of(filmToUpdate));

        val result = filmService.updateFilm(newFilm, filmId);

        assertEquals(newFilm, result);

        verify(filmRepository,times(1)).save(newFilm);
        verify(filmRepository, times(1)).findById(filmId);
    }

    @Test
    void removeFilm() {
        val filmId = 12L;
        val filmToDelete = Film.builder().title("trappola di cristallo").filmId(filmId).build();

        when(filmRepository.findById(filmId)).thenReturn(Optional.of(filmToDelete));

        val result = filmService.removeFilm(filmId);

        assertEquals(filmToDelete, result);
        verify(filmRepository, times(1)).findById(filmId);
        verify(filmRepository, times(1)).delete(filmToDelete);

    }
}