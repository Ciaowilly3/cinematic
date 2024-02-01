package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.GenreDto;
import com.cinematic.cinematic.mappers.GenreMapper;
import com.cinematic.cinematic.models.Genre;
import com.cinematic.cinematic.repositories.UserRepository;
import com.cinematic.cinematic.security.JwtService;
import com.cinematic.cinematic.security.MyUserDetailsService;
import com.cinematic.cinematic.services.impl.GenreServiceImpl;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GenreController.class)
@AutoConfigureMockMvc
@ContextConfiguration
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ResourceLoader resourceLoader;

    @MockBean
    private GenreServiceImpl genreService;
    @MockBean
    private GenreMapper genreMapper;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MyUserDetailsService myUserDetailsService;
    @MockBean
    private JwtService jwtService;

    private final String path = "/genres";

    @SneakyThrows
    @Test
    @WithMockUser
    void retrieveAllGenres() {
        val genre1 = Genre.builder().genreName("fantasy").build();
        val genre2 = Genre.builder().genreName("action").build();
        val genreList = List.of(genre1, genre2);
        when(genreService.retrieveAllGenres()).thenReturn(genreList);
        val genreDto1 = GenreDto.builder().genreName("fantasy").build();
        val genreDto2 = GenreDto.builder().genreName("action").build();
        val genreDtoList = List.of(genreDto1, genreDto2);
        when(genreMapper.toGenreDtos(genreList)).thenReturn(genreDtoList);

        val resource = resourceLoader.getResource("classpath:genre-list.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
        verify(genreService, times(1)).retrieveAllGenres();
    }
    @SneakyThrows
    @Test
    @WithMockUser
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
    @WithMockUser
    void makeGenre() throws Exception {
        val genre = Genre.builder().genreName("fantascienza").build();

        val resource = resourceLoader.getResource("classpath:genre-single.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(post(path)
                .contentType("application/json")
                        .with(csrf())
                .content(expectedJson))
                .andExpect(status().isCreated());
        verify(genreService, times(1)).makeGenre(genre);
    }

}