package com.cinematic.cinematic.dtos;

import com.cinematic.cinematic.models.Cinema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserDto {

    private String userName;

    private String role;

    private String email;

    private CinemaDto cinemaDto;
}