package com.cinematic.cinematic.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class SubscriptionDto {
    private LocalDate createdAt;

    private  LocalDate expiresAt;

    private Boolean expired;

    private UserDto userDto;
}
