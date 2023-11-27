package com.cinematic.cinematic.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class ReviewTroupeDto {

    private String review;

    private TroupeMemberDto troupeMemberDto;

    private UserDto userDto;
}
