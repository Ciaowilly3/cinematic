package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.GenreDto;
import com.cinematic.cinematic.models.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GenreMapper {

    GenreMapper INSTANCE = Mappers.getMapper(GenreMapper.class);

    GenreDto genreToGenreDto(Genre genre);

    List<GenreDto> genresToGenresDtos(List<Genre> genres);
}
