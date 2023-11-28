package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.ReviewTroupeDto;
import com.cinematic.cinematic.dtos.TicketDto;
import com.cinematic.cinematic.models.ReviewTroupe;
import com.cinematic.cinematic.models.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    @Mapping(source = "user", target = "userDto")
    @Mapping(source = "programmation", target = "programmationDto")
    TicketDto toTicketDto(Ticket ticket);
    List<TicketDto> toTicketsDtos(List<Ticket> tickets);
}
