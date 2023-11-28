package com.cinematic.cinematic.repositories;

import com.cinematic.cinematic.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
