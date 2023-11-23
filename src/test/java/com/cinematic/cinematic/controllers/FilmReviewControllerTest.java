package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.models.FilmReview;
import com.cinematic.cinematic.services.impl.FilmReviewServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FilmReviewController.class)
@AutoConfigureMockMvc
class FilmReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmReviewServiceImpl filmReviewService;

    private final String path = "/api/filmReviews";
    @Test
    void retrieveAllFilmReviews() throws Exception {
        val review1 = FilmReview.builder().review("trappola di cristallo").build();
        val review2 = FilmReview.builder().review("Rocky").build();
        val reviewList = List.of(review1, review2);

        when(filmReviewService.retrieveAllFilmReviews()).thenReturn(reviewList);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"review\" : \"trappola di cristallo\"}, {\"review\" : \"Rocky\"}]"));
        verify(filmReviewService, times(1)).retrieveAllFilmReviews();
    }
}