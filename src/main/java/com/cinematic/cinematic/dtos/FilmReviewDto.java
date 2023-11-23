package com.cinematic.cinematic.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class FilmReviewDto {

    private String review;

    private FilmDto filmDto;

    private UserDto userDto;
}
