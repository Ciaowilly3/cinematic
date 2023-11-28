package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.TicketDto;
import com.cinematic.cinematic.mappers.TicketMapper;
import com.cinematic.cinematic.services.impl.TicketServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("tickets")
@RequiredArgsConstructor
@RestController
public class TicketController {

    private final TicketServiceImpl ticketService;

    private final TicketMapper ticketMapper;

    @GetMapping
    public List<TicketDto> retrieveAllTickets(){
        return ticketMapper.toTicketsDtos(ticketService.retrieveAllTickets());
    }
}
