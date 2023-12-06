package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.CinemaDto;
import com.cinematic.cinematic.mappers.CinemaMapper;
import com.cinematic.cinematic.models.Cinema;
import com.cinematic.cinematic.repositories.UserRepository;
import com.cinematic.cinematic.security.JwtService;
import com.cinematic.cinematic.security.MyUserDetailsService;
import com.cinematic.cinematic.services.impl.CinemaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.test.context.annotation.SecurityTestExecutionListeners;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CinemaController.class)
@AutoConfigureMockMvc
@Slf4j
@ContextConfiguration
class CinemaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ResourceLoader resourceLoader;

    @MockBean
    private CinemaServiceImpl cinemaService;
    @MockBean
    private CinemaMapper cinemaMapper;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MyUserDetailsService myUserDetailsService;
    @MockBean
    private JwtService jwtService;

    private final String path = "/cinema";
    @Test
    @WithMockUser
    void retrieveAllCinema() throws Exception {
        val cinema1 = Cinema.builder().cinemaName("Cinestar").build();
        val cinema2 = Cinema.builder().cinemaName("udiCine").build();
        val cinemaList = List.of(cinema2,cinema1);
        when(cinemaService.retrieveAllCinema()).thenReturn(cinemaList);
        val cinemaDto1 = CinemaDto.builder().cinemaName("Cinestar").build();
        val cinemaDto2 = CinemaDto.builder().cinemaName("udiCine").build();
        val cinemaDtos = List.of(cinemaDto1, cinemaDto2);
        when(cinemaMapper.toCinemaDtos(cinemaList)).thenReturn(cinemaDtos);

        val resource = resourceLoader.getResource("classpath:cinema-list.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        log.info("json ottenuto: {}", expectedJson);
        log.info("json ottenuto: {}", expectedJson);
        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
        verify(cinemaService, times(1)).retrieveAllCinema();
    }

    @Test
    @WithMockUser
    void addCinema() throws Exception {
        val cinema = Cinema.builder().cinemaName("Cinestar").build();

        val resource = resourceLoader.getResource("classpath:cinema-request.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(post(path)
                .contentType("application/json")
                .content(expectedJson)
                        .with(csrf()))
                .andExpect(status().isCreated());
        verify(cinemaService, times(1)).addCinema(cinema);
    }
}
//TODO: è preferibile leggere i json da file in risorse FATTO da fare