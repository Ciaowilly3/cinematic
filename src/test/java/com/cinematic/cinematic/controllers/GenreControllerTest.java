package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.models.Genre;
import com.cinematic.cinematic.services.impl.GenreServiceImpl;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GenreController.class)
@AutoConfigureMockMvc
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreServiceImpl genreService;

    private final String path = "/api/genres";
    @SneakyThrows
    @Test
    void retrieveGenreById() {
        val genreId = 12L;
        val genre = Genre.builder().genreId(genreId).genreName("fantascienza").build();

        when(genreService.retrieveGenreById(genreId)).thenReturn(genre);

        mockMvc.perform(get(path + "/single-genre/{id}", genreId))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"genreName\" : \"fantascienza\"}"));
        verify(genreService, times(1)).retrieveGenreById(genreId);
    }
}