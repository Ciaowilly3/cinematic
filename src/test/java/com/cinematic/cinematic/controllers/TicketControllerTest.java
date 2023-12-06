package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.TicketDto;
import com.cinematic.cinematic.mappers.TicketMapper;
import com.cinematic.cinematic.models.Ticket;
import com.cinematic.cinematic.repositories.UserRepository;
import com.cinematic.cinematic.security.JwtService;
import com.cinematic.cinematic.security.MyUserDetailsService;
import com.cinematic.cinematic.services.impl.TicketServiceImpl;
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
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketController.class)
@AutoConfigureMockMvc
@ContextConfiguration
class TicketControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ResourceLoader resourceLoader;
    @MockBean
    private TicketServiceImpl ticketService;
    @MockBean
    private TicketMapper ticketMapper;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MyUserDetailsService myUserDetailsService;
    @MockBean
    private JwtService jwtService;

    private final String path = "/tickets";
    @Test
    @WithMockUser
    void retrieveAllTickets() throws Exception {
        val ticket1 = Ticket.builder().price(2.5).build();
        val ticket2 = Ticket.builder().price(3.0).build();
        val tickets = List.of(ticket1, ticket2);
        when(ticketService.retrieveAllTickets()).thenReturn(tickets);
        val ticketDto1 = TicketDto.builder().price(2.5).build();
        val ticketDto2 = TicketDto.builder().price(3.0).build();
        val ticketDtos = List.of(ticketDto1, ticketDto2);
        when(ticketMapper.toTicketsDtos(tickets)).thenReturn(ticketDtos);

        val resource = resourceLoader.getResource("classpath:ticket-list.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);


        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
        verify(ticketService, times(1)).retrieveAllTickets();
    }
}