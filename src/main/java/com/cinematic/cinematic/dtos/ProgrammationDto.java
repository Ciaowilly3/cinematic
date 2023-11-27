package com.cinematic.cinematic.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProgrammationDto {
    private LocalDateTime programmation;

    private CinemaDto cinemaDto;

    private FilmDto filmDto;
}
