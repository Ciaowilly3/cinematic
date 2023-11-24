package com.cinematic.cinematic.services;

import com.cinematic.cinematic.dtos.CreateFilmReviewRequestDto;
import com.cinematic.cinematic.models.ReviewFilm;

import java.util.List;

public interface ReviewFilmService {

    List<ReviewFilm> retrieveAllFilmReviews();

    void makeFilmReview(CreateFilmReviewRequestDto reviewRequestDto);
}
