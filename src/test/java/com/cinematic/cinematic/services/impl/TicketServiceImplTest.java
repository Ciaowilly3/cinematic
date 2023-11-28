package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.Ticket;
import com.cinematic.cinematic.repositories.TicketRepository;
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
class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;
    @Test
    void retrieveAllTickets() {
        val ticket1 = Ticket.builder().price(2.5).build();
        val ticket2 = Ticket.builder().price(3.0).build();
        val tickets = List.of(ticket1, ticket2);

        when(ticketRepository.findAll()).thenReturn(tickets);

        val result = ticketService.retrieveAllTickets();

        assertEquals(tickets, result);
        verify(ticketRepository, times(1)).findAll();
    }
}