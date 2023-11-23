package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.FilmDto;
import com.cinematic.cinematic.models.Film;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FilmMapper {
    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    FilmDto filmToFilmDto(Film film);

    List<FilmDto> filmsToFilmsDtos(List<Film> films);
}
//TODO: levare prefisso lasciare tox