package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.FilmDto;
import com.cinematic.cinematic.models.Film;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FilmMapper {
//    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);
    FilmDto toFilmDto(Film film);

    List<FilmDto> toFilmsDtos(List<Film> films);
}
//TODO: levare prefisso lasciare tox FATTO