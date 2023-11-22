package com.cinematic.cinematic.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class FilmDto {

    private String coverImg;

    private String title;

    private String nationOfProduction;

    private String plot;

    private Float rating;

    private String funFacts;

}
