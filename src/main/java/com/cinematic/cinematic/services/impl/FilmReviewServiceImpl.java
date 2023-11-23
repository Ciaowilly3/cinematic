package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.FilmReview;
import com.cinematic.cinematic.repositories.FilmReviewRepository;
import com.cinematic.cinematic.services.FilmReviewService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FilmReviewServiceImpl implements FilmReviewService {

    private final FilmReviewRepository filmReviewRepository;

    @Autowired
    public  FilmReviewServiceImpl(FilmReviewRepository filmReviewRepository){this.filmReviewRepository = filmReviewRepository;}

    public List<FilmReview> retrieveAllFilmReviews(){
        log.info("Start - retrieveAllFilmReviews - args:none");
        val filmReviews = filmReviewRepository.findAll();
        log.info("End - retrieveAllFilmReviews - out: {}", filmReviews);
        return filmReviews;
    }
}
