package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.GenreDto;
import com.cinematic.cinematic.models.Genre;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreDto toGenreDto(Genre genre);

}
