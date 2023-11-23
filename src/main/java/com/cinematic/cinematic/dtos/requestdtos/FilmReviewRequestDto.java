package com.cinematic.cinematic.dtos.requestdtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class FilmReviewRequestDto {
    private String review;
    private Long filmId;
    private Long userId;
}
