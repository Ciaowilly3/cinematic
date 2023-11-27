package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.ProgrammationDto;
import com.cinematic.cinematic.mappers.ProgrammationMapper;
import com.cinematic.cinematic.models.Programmation;
import com.cinematic.cinematic.services.impl.ProgrammationServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProgrammationController.class)
@AutoConfigureMockMvc
class ProgrammationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ResourceLoader resourceLoader;

    @MockBean
    private ProgrammationServiceImpl programmationService;
    @MockBean
    private ProgrammationMapper programmationMapper;

    private final String path = "/programmations";
    @Test
    void retrieveAllProgrammations() throws Exception {
        val now = LocalDateTime.of(2023,4,23,12,00,00);
        val programmation = List.of(Programmation.builder().programmation(now).build());
        when(programmationService.retrieveAllProgrammations()).thenReturn(programmation);
        val programmationDto = List.of(ProgrammationDto.builder().programmation(now).build());
        when(programmationMapper.toProgrammationsDtos(programmation)).thenReturn(programmationDto);

        val resource = resourceLoader.getResource("classpath:programmation-list.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
        verify(programmationService, times(1)).retrieveAllProgrammations();
    }
}