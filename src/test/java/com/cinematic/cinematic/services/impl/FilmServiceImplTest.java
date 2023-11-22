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
}