package com.cinematic.cinematic.dtos.requestdtos;

import com.cinematic.cinematic.models.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserRequestDto {

    private String userName;

    private String role;

    private String email;

    private String password;

    private Long cinemaId;
}
