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
    @Test
    void retrieveAllFilms() throws Exception {
        val film1 = Film.builder().title("trappola di cristallo").build();
        val film2 = Film.builder().title("Rocky").build();
        val filmList = List.of(film1, film2);
        when(filmService.retrieveAllFilms()).thenReturn(filmList);

        mockMvc.perform(get("/api/films"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"title\" : \"trappola di cristallo\"}, {\"title\" : \"Rocky\"}]"));
        verify(filmService, times(1)).retrieveAllFilms();
    }

    @Test
    void retrieveFilmsByTitle() throws Exception {
        val film = Film.builder().title("Rocky").build();
        when(filmService.retrieveFilmsByTitle("Ro")).thenReturn(List.of(film));

        mockMvc.perform(get("/api/films/{title}", "Ro"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"title\" : \"Rocky\"}]"));
        verify(filmService, times(1)).retrieveFilmsByTitle("Ro");
    }
}