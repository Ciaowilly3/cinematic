package com.cinematic.cinematic.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class TroupeMemberDto {

    private String profilePic;

    private String memberName;

    private String nationality;

    private Float rating;

    private String biography;

    private String role;

    private String funFacts;
}
