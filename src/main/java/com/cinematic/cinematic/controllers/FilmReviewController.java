package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.FilmReviewDto;
import com.cinematic.cinematic.dtos.requestdtos.FilmReviewRequestDto;
import com.cinematic.cinematic.mappers.FilmMapper;
import com.cinematic.cinematic.mappers.FilmReviewMapper;
import com.cinematic.cinematic.services.impl.FilmReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("filmReviews")
@RestController
public class FilmReviewController {

    private final FilmReviewServiceImpl filmReviewService;

    @Autowired
    public FilmReviewController(FilmReviewServiceImpl filmReviewService){this.filmReviewService = filmReviewService;}

    @GetMapping
    public List<FilmReviewDto> retrieveAllFilmReviews(){
        return FilmReviewMapper.INSTANCE.filmReviewsToFilmReviewsDtos(filmReviewService.retrieveAllFilmReviews());
    }

    @PostMapping
    public void makeFilmReview(@RequestBody FilmReviewRequestDto requestDto){
        filmReviewService.makeFilmReview(requestDto);
    }
}
// TODO: rendere review entità principale cambiando nome
