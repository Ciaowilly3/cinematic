package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.CinemaDto;
import com.cinematic.cinematic.models.Cinema;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CinemaMapper {
    CinemaDto toCinemaDto(Cinema cinema);

    List<CinemaDto> toCinemaDtos(List<Cinema> cinemaList);
}
