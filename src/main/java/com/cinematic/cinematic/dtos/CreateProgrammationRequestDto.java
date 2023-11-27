package com.cinematic.cinematic.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateProgrammationRequestDto {
    private LocalDateTime programmation;

    private Long filmId;
    private Long cinemaId;
}
