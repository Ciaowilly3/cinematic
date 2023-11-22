package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.CinemaDto;
import com.cinematic.cinematic.models.Cinema;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CinemaMapper {

    CinemaMapper INSTANCE = Mappers.getMapper(CinemaMapper.class);

    CinemaDto cinemaToCinemaDto(Cinema cinema);

    List<CinemaDto> cinemaToCinemaDtos(List<Cinema> cinemaList);
}
