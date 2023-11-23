package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.models.FilmReview;
import com.cinematic.cinematic.repositories.FilmReviewRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmReviewServiceImplTest {

    @Mock
    private FilmReviewRepository filmReviewRepository;

    @InjectMocks
    private  FilmReviewServiceImpl filmReviewService;
    @Test
    void retrieveAllFilmReviews() {
        val review1 = FilmReview.builder().review("trappola di cristallo").build();
        val review2 = FilmReview.builder().review("Rocky").build();
        val reviewList = List.of(review1, review2);

        when(filmReviewRepository.findAll()).thenReturn(reviewList);

        val result = filmReviewService.retrieveAllFilmReviews();

        assertEquals(reviewList, result);
        verify(filmReviewRepository, times(1)).findAll();
    }
}