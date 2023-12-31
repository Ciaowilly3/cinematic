package com.cinematic.cinematic.dtos;

import com.cinematic.cinematic.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateUserRequestDto {

    private String userName;

    private Role role;

    private String email;

    private String password;

    private Long cinemaId;
}
