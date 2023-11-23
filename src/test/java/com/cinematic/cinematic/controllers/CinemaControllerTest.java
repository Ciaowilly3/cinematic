package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.models.Cinema;
import com.cinematic.cinematic.services.impl.CinemaServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CinemaController.class)
@AutoConfigureMockMvc
class CinemaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CinemaServiceImpl cinemaService;

    private final String path = "/cinema";
    @Test
    void retrieveAllCinema() throws Exception {
        val cinema1 = Cinema.builder().cinemaName("Cinestar").build();
        val cinema2 = Cinema.builder().cinemaName("udiCine").build();
        val cinemaList = List.of(cinema2,cinema1);
        when(cinemaService.retrieveAllCinema()).thenReturn(cinemaList);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"cinemaName\" : \"Cinestar\"}, {\"cinemaName\" : \"udiCine\"}]"));
        verify(cinemaService, times(1)).retrieveAllCinema();
    }

    @Test
    void addCinema() throws Exception {
        val cinema = Cinema.builder().cinemaName("Cinestar").build();

        mockMvc.perform(post(path)
                .contentType("application/json")
                .content("{\"cinemaName\" : \"Cinestar\"}"))
                .andExpect(status().isOk());
        verify(cinemaService, times(1)).addCinema(cinema);
    }
}
//TODO: è preferibile leggere i json da file in risorse