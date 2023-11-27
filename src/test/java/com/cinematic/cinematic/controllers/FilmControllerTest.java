package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.FilmDto;
import com.cinematic.cinematic.mappers.FilmMapper;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.services.impl.FilmServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(FilmController.class)
@AutoConfigureMockMvc
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ResourceLoader resourceLoader;
    @MockBean
    private FilmServiceImpl filmService;
    @MockBean
    private FilmMapper filmMapper;
    private final String path = "/films";
    @Test
    void retrieveAllFilms() throws Exception {
        val film1 = Film.builder().title("trappola di cristazzo").build();
        val film2 = Film.builder().title("Rocky").build();
        val filmList = List.of(film1, film2);
        when(filmService.retrieveAllFilms()).thenReturn(filmList);
        val filmDto1 = FilmDto.builder().title("trappola di cristallo").build();
        val filmDto2 = FilmDto.builder().title("Rocky").build();
        val filmDtoList = List.of(filmDto1, filmDto2);
        when(filmMapper.toFilmsDtos(filmList)).thenReturn(filmDtoList);

        val resource = resourceLoader.getResource("classpath:film-list.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
        verify(filmService, times(1)).retrieveAllFilms();
    }

    @Test
    void retrieveFilmsByTitle() throws Exception {
        val film = Film.builder().title("Rocky").build();
        when(filmService.retrieveFilmsByTitle("Ro")).thenReturn(List.of(film));
        val filmDto = FilmDto.builder().title("Rocky").build();
        when(filmMapper.toFilmsDtos(List.of(film))).thenReturn(List.of(filmDto));


        val resource = resourceLoader.getResource("classpath:films-to-retrieve.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(get(path + "/{title}", "Ro"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
        verify(filmService, times(1)).retrieveFilmsByTitle("Ro");
    }

    @Test
    void retrieveFilmById() throws Exception{
        val filmId = 12L;
        val film = Film.builder().title("Rocky").filmId(filmId).build();
        when(filmService.retrieveFilmById(filmId)).thenReturn(film);
        val filmDto = FilmDto.builder().title("Rocky").build();
        when(filmMapper.toFilmDto(film)).thenReturn(filmDto);


        val resource = resourceLoader.getResource("classpath:film-single.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(get(path + "/single-film/{id}", filmId))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
        verify(filmService, times(1)).retrieveFilmById(filmId);
    }

    @Test
    void makeNewFilm() throws Exception {
        val film = Film.builder().title("Rocky").build();

        val resource = resourceLoader.getResource("classpath:film-single.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(post(path)
                .contentType("application/json")
                .content(expectedJson))
                .andExpect(status().isCreated());

        verify(filmService, times(1)).makeNewFilm(film);
    }

    @Test
    void updateFilm() throws Exception {
        val filmId = 12L;

        val newFilm = Film.builder().title("Rocky").build();

        val resource = resourceLoader.getResource("classpath:film-single.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(put(path + "/update-film/{id}", filmId)
                .contentType("application/json")
                .content(expectedJson))
                .andExpect(status().isAccepted());

        verify(filmService, times(1)).updateFilm(newFilm, filmId);
    }
}