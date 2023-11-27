package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.GenreDto;
import com.cinematic.cinematic.mappers.GenreMapper;
import com.cinematic.cinematic.models.Genre;
import com.cinematic.cinematic.services.impl.GenreServiceImpl;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GenreController.class)
@AutoConfigureMockMvc
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ResourceLoader resourceLoader;

    @MockBean
    private GenreServiceImpl genreService;
    @MockBean
    private GenreMapper genreMapper;

    private final String path = "/genres";
    @SneakyThrows
    @Test
    void retrieveGenreById() {
        val genreId = 12L;
        val genre = Genre.builder().genreId(genreId).genreName("fantascienza").build();
        when(genreService.retrieveGenreById(genreId)).thenReturn(genre);
        val genreDto = GenreDto.builder().genreName("fantascienza").build();
        when(genreMapper.toGenreDto(genre)).thenReturn(genreDto);

        val resource = resourceLoader.getResource("classpath:genre-single.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(get(path + "/single-genre/{id}", genreId))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
        verify(genreService, times(1)).retrieveGenreById(genreId);
    }

    @Test
    void makeGenre() {
        val genre = Genre.builder().genreName("fantascienza").build();

    }
}