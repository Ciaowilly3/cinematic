package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.Cinema;
import com.cinematic.cinematic.repositories.CinemaRepository;
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
class CinemaServiceImplTest {

    @Mock
    private CinemaRepository cinemaRepository;

    @InjectMocks
    private CinemaServiceImpl cinemaService;
    @Test
    void retrieveAllCinema() {
        val cinema1 = Cinema.builder().cinemaName("Cinestar").build();
        val cinema2 = Cinema.builder().cinemaName("udiCine").build();
        val cinemaList = List.of(cinema2,cinema1);
        when(cinemaRepository.findAll()).thenReturn(cinemaList);

        val result = cinemaService.retrieveAllCinema();

        assertEquals(cinemaList, result);
        verify(cinemaRepository, times(1)).findAll();
    }

    @Test
    void addCinema() {
        val cinema = Cinema.builder().cinemaName("Cinestar").build();

        cinemaService.addCinema(cinema);

        verify(cinemaRepository, times(1)).save(cinema);
    }
}