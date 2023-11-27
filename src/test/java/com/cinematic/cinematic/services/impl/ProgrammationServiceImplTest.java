package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.Programmation;
import com.cinematic.cinematic.repositories.ProgrammationRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProgrammationServiceImplTest {
    @Mock
    private ProgrammationRepository programmationRepository;

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
    }
}