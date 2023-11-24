package com.cinematic.cinematic.services;

import com.cinematic.cinematic.dtos.CreateFilmReviewRequestDto;
import com.cinematic.cinematic.models.FilmReview;

import java.util.List;

public interface FilmReviewService {

    public List<FilmReview> retrieveAllFilmReviews();

    public void makeFilmReview(CreateFilmReviewRequestDto reviewRequestDto);
}
