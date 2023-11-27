package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.dtos.CreateProgrammationRequestDto;
import com.cinematic.cinematic.models.Cinema;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.models.Programmation;
import com.cinematic.cinematic.models.User;
import com.cinematic.cinematic.repositories.CinemaRepository;
import com.cinematic.cinematic.repositories.FilmRepository;
import com.cinematic.cinematic.repositories.ProgrammationRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProgrammationServiceImplTest {
    @Mock
    private ProgrammationRepository programmationRepository;
    @Mock
    private FilmRepository filmRepository;
    @Mock
    private CinemaRepository cinemaRepository;

    @InjectMocks
    private ProgrammationServiceImpl programmationService;
    @Test
    void retrieveAllProgrammations() {
        val now = LocalDateTime.now();
        val programmation = List.of(Programmation.builder().programmation(now).build());
        when(programmationRepository.findAll()).thenReturn(programmation);

        val result = programmationService.retrieveAllProgrammations();

        assertEquals(programmation, result);
        verify(programmationRepository, times(1)).findAll();
    }

    @Test
    void makeProgrammation() {
        val now = LocalDateTime.now();
        val id= 12L;
        val film = Film.builder().title("titolo").filmId(id).build();
        val cinema = Cinema.builder().cinemaName("portali").cinemaId(id).build();
        when(filmRepository.findById(id)).thenReturn(Optional.of(film));
        when(cinemaRepository.findById(id)).thenReturn(Optional.of(cinema));
        val programmation = Programmation.builder().film(film).cinema(cinema).programmation(now).build();
        val request = CreateProgrammationRequestDto.builder().programmation(now).cinemaId(id).filmId(id).build();
        val result = programmationService.makeProgrammation(request);

        assertEquals(programmation, result);
        verify(filmRepository, times(1)).findById(id);
        verify(cinemaRepository, times(1)).findById(id);
        verify(programmationRepository, times(1)).save(programmation);
    }
}