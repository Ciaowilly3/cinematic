package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.TicketDto;
import com.cinematic.cinematic.mappers.TicketMapper;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.models.Ticket;
import com.cinematic.cinematic.services.impl.TicketServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("tickets")
@RequiredArgsConstructor
@RestController
@Tag(name = "controller for tickets", description = "makes a bunch of staff for tickets")
public class TicketController {

    private final TicketServiceImpl ticketService;

    private final TicketMapper ticketMapper;

    @Operation(summary = "Get a list of tickets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list of tickets",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Ticket.class)) }),
            @ApiResponse(responseCode = "404", description = "tickets list not found",
                    content = @Content) })
    @GetMapping
    public List<TicketDto> retrieveAllTickets(){
        return ticketMapper.toTicketsDtos(ticketService.retrieveAllTickets());
    }
}
