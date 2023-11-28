package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.Ticket;
import com.cinematic.cinematic.repositories.TicketRepository;
import com.cinematic.cinematic.services.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public List<Ticket> retrieveAllTickets(){
        log.info("Start - retrieveAllTickets - args:none");
        val tickets = ticketRepository.findAll();
        log.info("End - retrieveAllTickets - out: {}", tickets.size());
        return tickets;
    }
}
