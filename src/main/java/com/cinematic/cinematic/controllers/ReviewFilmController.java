package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.FilmReviewDto;
import com.cinematic.cinematic.dtos.requestdtos.FilmReviewRequestDto;
import com.cinematic.cinematic.mappers.FilmReviewMapper;
import com.cinematic.cinematic.services.impl.FilmReviewServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("review-films")
@RequiredArgsConstructor
@RestController
public class ReviewFilmController {

    private final FilmReviewServiceImpl filmReviewService;
    private final FilmReviewMapper filmReviewMapper;

    @GetMapping
    public List<FilmReviewDto> retrieveAllFilmReviews(){
        return filmReviewMapper.filmReviewsToFilmReviewsDtos(filmReviewService.retrieveAllFilmReviews());
    }

    @PostMapping
    public void makeFilmReview(@RequestBody FilmReviewRequestDto requestDto){
        filmReviewService.makeFilmReview(requestDto);
    }
}
// TODO: rendere review entità principale cambiando nome FATTO
