package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.CinemaDto;
import com.cinematic.cinematic.models.Cinema;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CinemaMapper {
    CinemaDto cinemaToCinemaDto(Cinema cinema);

    List<CinemaDto> cinemaToCinemaDtos(List<Cinema> cinemaList);
}
