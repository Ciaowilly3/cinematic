package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.ReviewFilmDto;
import com.cinematic.cinematic.dtos.CreateFilmReviewRequestDto;
import com.cinematic.cinematic.mappers.ReviewFilmMapper;
import com.cinematic.cinematic.services.impl.ReviewFilmServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("review-films")
@RequiredArgsConstructor
@RestController
public class ReviewFilmController {

    private final ReviewFilmServiceImpl reviewFilmService;
    private final ReviewFilmMapper reviewFilmMapper;

    @GetMapping
    public List<ReviewFilmDto> retrieveAllFilmReviews(){
        return reviewFilmMapper.toFilmReviewsDtos(reviewFilmService.retrieveAllFilmReviews());
    }

    @PostMapping
    public void makeFilmReview(@RequestBody CreateFilmReviewRequestDto requestDto){
        reviewFilmService.makeFilmReview(requestDto);
    }
}
// TODO: rendere review entità principale cambiando nome FATTO
