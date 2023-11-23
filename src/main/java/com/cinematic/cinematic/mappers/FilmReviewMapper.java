package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.FilmReviewDto;
import com.cinematic.cinematic.models.FilmReview;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FilmReviewMapper {

    FilmReviewMapper INSTANCE = Mappers.getMapper(FilmReviewMapper.class);

    @Mapping(source = "film", target = "filmDto")
    @Mapping(source = "user", target = "userDto")
    public FilmReviewDto filmReviewToFilmReviewDto(FilmReview filmReview);

    public List<FilmReviewDto> filmReviewsToFilmReviewsDtos(List<FilmReview> filmReviews);
}
