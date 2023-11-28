package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.dtos.CreateReviewTroupeDto;
import com.cinematic.cinematic.models.ReviewTroupe;
import com.cinematic.cinematic.models.TroupeMember;
import com.cinematic.cinematic.models.User;
import com.cinematic.cinematic.repositories.ReviewTroupeRepository;
import com.cinematic.cinematic.repositories.TroupeMemberRepository;
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
class ReviewTroupeServiceImplTest {

    @Mock
    private ReviewTroupeRepository reviewTroupeRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TroupeMemberRepository troupeMemberRepository;
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

    @Test
    void makeReviewTroupe() {
        val id = 12L;
        val member = TroupeMember.builder().troupeMemberId(id).memberName("marco").build();
        val user = User.builder().userName("marco").userId(id).build();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(troupeMemberRepository.findById(id)).thenReturn(Optional.of(member));

        val review = ReviewTroupe.builder().review("trappola di cristallo").troupeMember(member).user(user).build();
        val request = CreateReviewTroupeDto.builder().review("trappola di cristallo").memberId(id).userId(id).build();
        val result = reviewTroupeService.makeReviewTroupe(request);

        assertEquals(review, result);
        verify(reviewTroupeRepository, times(1)).save(result);
    }
}