package com.cinematic.cinematic.services;

import com.cinematic.cinematic.models.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> retrieveAllTickets();
}
