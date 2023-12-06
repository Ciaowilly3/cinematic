package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.CreateReviewTroupeDto;
import com.cinematic.cinematic.dtos.ReviewTroupeDto;
import com.cinematic.cinematic.mappers.ReviewTroupeMapper;
import com.cinematic.cinematic.models.ReviewTroupe;
import com.cinematic.cinematic.repositories.UserRepository;
import com.cinematic.cinematic.security.JwtService;
import com.cinematic.cinematic.security.MyUserDetailsService;
import com.cinematic.cinematic.services.impl.ReviewTroupeServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewTroupeController.class)
@AutoConfigureMockMvc
@ContextConfiguration
class ReviewTroupeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ResourceLoader resourceLoader;
    @MockBean
    private ReviewTroupeServiceImpl reviewTroupeService;
    @MockBean
    private ReviewTroupeMapper reviewTroupeMapper;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MyUserDetailsService myUserDetailsService;
    @MockBean
    private JwtService jwtService;

    private final String path = "/review-troupes";
    @Test
    @WithMockUser
    void retrieveAllReviewsTroupe() throws Exception {
        val review1 = ReviewTroupe.builder().review("anna gigle").build();
        val review2 = ReviewTroupe.builder().review("baky").build();
        val reviewList = List.of(review1, review2);
        when(reviewTroupeService.retrieveAllReviewsTroupe()).thenReturn(reviewList);
        val reviewDto1 = ReviewTroupeDto.builder().review("anna gigle").build();
        val reviewDto2 = ReviewTroupeDto.builder().review("baky").build();
        val reviewDtoList = List.of(reviewDto1, reviewDto2);
        when(reviewTroupeMapper.toReviewTroupeDtos(reviewList)).thenReturn(reviewDtoList);

        val resource = resourceLoader.getResource("classpath:review-troupe-list.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);


        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
        verify(reviewTroupeService, times(1)).retrieveAllReviewsTroupe();
    }

    @Test
    @WithMockUser
    void makeReviewTroupe() throws Exception {
        val review = ReviewTroupe.builder().review("cameron").build();
        val request = CreateReviewTroupeDto.builder().review("cameron").build();

        val resource = resourceLoader.getResource("classpath:review-troupe-single.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(post(path)
                .contentType("application/json")
                        .with(csrf())
                .content(expectedJson))
                .andExpect(status().isCreated());
        verify(reviewTroupeService, times(1)).makeReviewTroupe(request);
    }
}
