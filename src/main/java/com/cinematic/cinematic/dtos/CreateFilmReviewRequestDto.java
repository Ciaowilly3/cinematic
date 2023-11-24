package com.cinematic.cinematic.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CreateFilmReviewRequestDto {
    private String review;
    private Long filmId;
    private Long userId;
}
