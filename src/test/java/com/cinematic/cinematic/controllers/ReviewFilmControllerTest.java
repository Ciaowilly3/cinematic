package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.CreateFilmReviewRequestDto;
import com.cinematic.cinematic.dtos.ReviewFilmDto;
import com.cinematic.cinematic.mappers.ReviewFilmMapper;
import com.cinematic.cinematic.models.ReviewFilm;
import com.cinematic.cinematic.repositories.UserRepository;
import com.cinematic.cinematic.security.JwtService;
import com.cinematic.cinematic.security.MyUserDetailsService;
import com.cinematic.cinematic.services.impl.ReviewFilmServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewFilmController.class)
@AutoConfigureMockMvc
@ContextConfiguration
class ReviewFilmControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ResourceLoader resourceLoader;
    @MockBean
    private ReviewFilmServiceImpl filmReviewService;
    @MockBean
    private ReviewFilmMapper reviewFilmMapper;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MyUserDetailsService myUserDetailsService;
    @MockBean
    private JwtService jwtService;
    private final String path = "/review-films";
    @Test
    @WithMockUser
    void retrieveAllFilmReviews() throws Exception {
        val review1 = ReviewFilm.builder().review("trappola di cristallo").build();
        val review2 = ReviewFilm.builder().review("Rocky").build();
        val reviewList = List.of(review1, review2);
        when(filmReviewService.retrieveAllFilmReviews()).thenReturn(reviewList);
        val reviewDto1 = ReviewFilmDto.builder().review("trappola di cristallo").build();
        val reviewDto2 = ReviewFilmDto.builder().review("Rocky").build();
        val reviewDtoList = List.of(reviewDto1, reviewDto2);
        when(reviewFilmMapper.toFilmReviewsDtos(reviewList)).thenReturn(reviewDtoList);

        val resource = resourceLoader.getResource("classpath:reviews-films-list.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);


        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
        verify(filmReviewService, times(1)).retrieveAllFilmReviews();
    }

    @Test
    @WithMockUser
    void makeFilmReview() throws Exception{
        val review = CreateFilmReviewRequestDto.builder().review("reviu").build();

        val resource = resourceLoader.getResource("classpath:review-single.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(post(path)
                .contentType("application/json")
                        .with(csrf())
                .content(expectedJson))
                .andExpect(status().isCreated());

        verify(filmReviewService, times(1)).makeFilmReview(review);
    }
}