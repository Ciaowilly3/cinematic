package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.GenreDto;
import com.cinematic.cinematic.models.Genre;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDto toGenreDto(Genre genre);

    List<GenreDto> toGenreDtos(List<Genre> genreList);
}
