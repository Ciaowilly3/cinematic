package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.dtos.CreateFilmReviewRequestDto;
import com.cinematic.cinematic.exceptions.NotFoundException;
import com.cinematic.cinematic.models.ReviewFilm;
import com.cinematic.cinematic.repositories.FilmRepository;
import com.cinematic.cinematic.repositories.ReviewFilmRepository;
import com.cinematic.cinematic.repositories.UserRepository;
import com.cinematic.cinematic.services.ReviewFilmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewFilmServiceImpl implements ReviewFilmService {

    private final ReviewFilmRepository reviewFilmRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    public List<ReviewFilm> retrieveAllFilmReviews(){
        log.info("Start - retrieveAllFilmReviews - args:none");
        val filmReviews = reviewFilmRepository.findAll();
        log.info("End - retrieveAllFilmReviews - out: {}", filmReviews.size());
        return filmReviews;
    }

    public void makeFilmReview(CreateFilmReviewRequestDto requestDto){
        log.info("Start - makeFilmReview - args: reviewRequest: {}", requestDto);
        val film = filmRepository.findById(requestDto.getFilmId());
        val user = userRepository.findById(requestDto.getUserId());
        val review = ReviewFilm.builder()
                .review(requestDto.getReview())
                .film(film.orElseThrow(() -> new NotFoundException("userId doesn't match any record" + requestDto.getUserId())))
                .user(user.orElseThrow(() -> new NotFoundException("filmId doesn't match any record" + requestDto.getFilmId())))
                .build();
        reviewFilmRepository.save(review);
        log.info("End - makeFilmReview - out: review,film,user {}{}{}", review, film, user);
    }
}
//TODO: assicurarsi che la loggata di end sia l'ultimo avvenimento all'interno del metodo FATTO