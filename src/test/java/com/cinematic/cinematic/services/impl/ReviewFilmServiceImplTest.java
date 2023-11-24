package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.dtos.CreateFilmReviewRequestDto;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.models.ReviewFilm;
import com.cinematic.cinematic.models.User;
import com.cinematic.cinematic.repositories.FilmRepository;
import com.cinematic.cinematic.repositories.ReviewFilmRepository;
import com.cinematic.cinematic.repositories.UserRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewFilmServiceImplTest {

    @Mock
    private ReviewFilmRepository reviewFilmRepository;
    @Mock
    private FilmRepository filmRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReviewFilmServiceImpl filmReviewService;
    @Test
    void retrieveAllFilmReviews() {
        val review1 = ReviewFilm.builder().review("trappola di cristallo").build();
        val review2 = ReviewFilm.builder().review("Rocky").build();
        val reviewList = List.of(review1, review2);

        when(reviewFilmRepository.findAll()).thenReturn(reviewList);

        val result = filmReviewService.retrieveAllFilmReviews();

        assertEquals(reviewList, result);
        verify(reviewFilmRepository, times(1)).findAll();
    }

    @Test
    void makeFilmReview() {
        val filmId= 12L;
        val userId= 12L;
        val film = Film.builder().title("titolo").filmId(filmId).build();
        val user = User.builder().userName("nome").userId(filmId).build();
        val reviewRequest = CreateFilmReviewRequestDto.builder().review("trappola di cristallo").filmId(filmId).userId(userId).build();
        when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        filmReviewService.makeFilmReview(reviewRequest);

        val review = ReviewFilm.builder().review(reviewRequest.getReview()).film(film).user(user).build();

        verify(reviewFilmRepository,times(1)).save(review);
    }
}