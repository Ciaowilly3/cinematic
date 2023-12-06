package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.CreateProgrammationRequestDto;
import com.cinematic.cinematic.dtos.ProgrammationDto;
import com.cinematic.cinematic.mappers.ProgrammationMapper;
import com.cinematic.cinematic.models.Programmation;
import com.cinematic.cinematic.repositories.UserRepository;
import com.cinematic.cinematic.security.JwtService;
import com.cinematic.cinematic.security.MyUserDetailsService;
import com.cinematic.cinematic.services.impl.ProgrammationServiceImpl;
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

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProgrammationController.class)
@AutoConfigureMockMvc
@ContextConfiguration
class ProgrammationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ResourceLoader resourceLoader;

    @MockBean
    private ProgrammationServiceImpl programmationService;
    @MockBean
    private ProgrammationMapper programmationMapper;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MyUserDetailsService myUserDetailsService;
    @MockBean
    private JwtService jwtService;

    private final String path = "/programmations";
    @Test
    @WithMockUser
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

    @Test
    @WithMockUser
    void makeProgrammation() throws Exception {
        val now = LocalDateTime.of(2023,4,23,12,00,00);
        val programmationRequest = CreateProgrammationRequestDto.builder().programmation(now).build();
        val programmation = Programmation.builder().programmation(now).build();
        when(programmationService.makeProgrammation(programmationRequest)).thenReturn(programmation);
        val programmationDto = ProgrammationDto.builder().programmation(now).build();
        when(programmationMapper.toProgrammationDto(programmation)).thenReturn(programmationDto);

        val resource = resourceLoader.getResource("classpath:programmation-single.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(post(path)
                .contentType("application/json")
                        .with(csrf())
                .content(expectedJson))
                .andExpect(status().isCreated());
        verify(programmationService, times(1)).makeProgrammation(programmationRequest);
    }
}