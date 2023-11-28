package com.cinematic.cinematic.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CreateReviewTroupeDto {
    private String review;
    private Long userId;
    private Long memberId;
}
