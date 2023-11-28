package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.ReviewTroupeDto;
import com.cinematic.cinematic.mappers.ReviewTroupeMapper;
import com.cinematic.cinematic.models.ReviewTroupe;
import com.cinematic.cinematic.services.impl.ReviewTroupeServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewTroupeController.class)
@AutoConfigureMockMvc
class ReviewTroupeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ResourceLoader resourceLoader;
    @MockBean
    private ReviewTroupeServiceImpl reviewTroupeService;
    @MockBean
    private ReviewTroupeMapper reviewTroupeMapper;

    private final String path = "/review-troupes";
    @Test
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
}
