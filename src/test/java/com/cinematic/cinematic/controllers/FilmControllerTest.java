package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.services.impl.FilmServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(FilmController.class)
@AutoConfigureMockMvc
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmServiceImpl filmService;

    private final String path = "/films";
    @Test
    void retrieveAllFilms() throws Exception {
        val film1 = Film.builder().title("trappola di cristallo").build();
        val film2 = Film.builder().title("Rocky").build();
        val filmList = List.of(film1, film2);
        when(filmService.retrieveAllFilms()).thenReturn(filmList);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"title\" : \"trappola di cristallo\"}, {\"title\" : \"Rocky\"}]"));
        verify(filmService, times(1)).retrieveAllFilms();
    }

    @Test
    void retrieveFilmsByTitle() throws Exception {
        val film = Film.builder().title("Rocky").build();
        when(filmService.retrieveFilmsByTitle("Ro")).thenReturn(List.of(film));

        mockMvc.perform(get(path + "/{title}", "Ro"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"title\" : \"Rocky\"}]"));
        verify(filmService, times(1)).retrieveFilmsByTitle("Ro");
    }

    @Test
    void retrieveFilmById() throws Exception{
        val filmId = 12L;
        val film = Film.builder().title("Rocky").filmId(filmId).build();
        when(filmService.retrieveFilmById(filmId)).thenReturn(film);

        mockMvc.perform(get(path + "/single-film/{id}", filmId))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"title\" : \"Rocky\"}"));
        verify(filmService, times(1)).retrieveFilmById(filmId);
    }

    @Test
    void makeNewFilm() throws Exception {
        val film = Film.builder().title("Rocky").build();

        mockMvc.perform(post(path)
                .contentType("application/json")
                .content("{\"title\" : \"Rocky\"}"))
                .andExpect(status().isOk());

        verify(filmService, times(1)).makeNewFilm(film);
    }
}