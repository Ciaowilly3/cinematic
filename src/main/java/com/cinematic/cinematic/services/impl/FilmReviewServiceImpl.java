package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.dtos.requestdtos.FilmReviewRequestDto;
import com.cinematic.cinematic.exceptions.NotFoundException;
import com.cinematic.cinematic.models.FilmReview;
import com.cinematic.cinematic.repositories.FilmRepository;
import com.cinematic.cinematic.repositories.FilmReviewRepository;
import com.cinematic.cinematic.repositories.UserRepository;
import com.cinematic.cinematic.services.FilmReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilmReviewServiceImpl implements FilmReviewService {

    private final FilmReviewRepository filmReviewRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    public List<FilmReview> retrieveAllFilmReviews(){
        log.info("Start - retrieveAllFilmReviews - args:none");
        val filmReviews = filmReviewRepository.findAll();
        log.info("End - retrieveAllFilmReviews - out: {}", filmReviews);
        return filmReviews;
    }

    public void makeFilmReview(FilmReviewRequestDto requestDto){
        log.info("Start - makeFilmReview - args: reviewRequest: {}", requestDto);
        val film = filmRepository.findById(requestDto.getFilmId());
        val user = userRepository.findById(requestDto.getUserId());
        if (film.isEmpty() || user.isEmpty()){
            throw new NotFoundException("userId or filmId doesn't match any record");
        }
        val review = FilmReview.builder().review(requestDto.getReview()).film(film.get()).user(user.get()).build();
        log.info("End - makeFilmReview - out: review,film,user {}{}{}", review, film, user);
        filmReviewRepository.save(review);
    }
}
//TODO: assicurarsi che la loggata di end sia l'ultimo avvenimento all'interno del metodo