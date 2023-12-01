package com.cinematic.cinematic.dtos;

import com.cinematic.cinematic.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegisterRequest {
    private String userName;
    private String email;
    private Role role;
    private String password;

}
