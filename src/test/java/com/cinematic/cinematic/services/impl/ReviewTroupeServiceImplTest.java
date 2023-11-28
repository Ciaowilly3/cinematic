package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.ReviewTroupe;
import com.cinematic.cinematic.repositories.ReviewTroupeRepository;
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
class ReviewTroupeServiceImplTest {

    @Mock
    private ReviewTroupeRepository reviewTroupeRepository;
    @InjectMocks
    private ReviewTroupeServiceImpl reviewTroupeService;
    @Test
    void retrieveAllReviewsTroupe() {
        val review1 = ReviewTroupe.builder().review("trappola di cristallo").build();
        val review2 = ReviewTroupe.builder().review("Rocky").build();
        val reviewList = List.of(review1, review2);

        when(reviewTroupeRepository.findAll()).thenReturn(reviewList);

        val result = reviewTroupeService.retrieveAllReviewsTroupe();

        assertEquals(reviewList, result);
        verify(reviewTroupeRepository, times(1)).findAll();
    }
}