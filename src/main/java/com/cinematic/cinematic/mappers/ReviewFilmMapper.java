package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.ReviewFilmDto;
import com.cinematic.cinematic.models.ReviewFilm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewFilmMapper {
    @Mapping(source = "film", target = "filmDto")
    @Mapping(source = "user", target = "userDto")
    ReviewFilmDto ToFilmReviewDto(ReviewFilm reviewFilm);
    List<ReviewFilmDto> toFilmReviewsDtos(List<ReviewFilm> reviewFilms);
}
//TODO: sistemare i warning
//TODO: togliere il mapping di review e capire perchè non funziona FATTO