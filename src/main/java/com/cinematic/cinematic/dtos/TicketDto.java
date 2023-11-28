package com.cinematic.cinematic.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class TicketDto {
    private Double price;

    private UserDto userDto;

    private ProgrammationDto programmationDto;
}
