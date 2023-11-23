package com.cinematic.cinematic.dtos.requestdtos;

import lombok.Data;

@Data
public class FilmReviewRequestDto {
    private String review;
    private Long filmId;
    private Long userId;
}
