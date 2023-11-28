package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.dtos.CreateReviewTroupeDto;
import com.cinematic.cinematic.exceptions.NotFoundException;
import com.cinematic.cinematic.models.ReviewTroupe;
import com.cinematic.cinematic.repositories.ReviewTroupeRepository;
import com.cinematic.cinematic.repositories.TroupeMemberRepository;
import com.cinematic.cinematic.repositories.UserRepository;
import com.cinematic.cinematic.services.ReviewTroupeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewTroupeServiceImpl implements ReviewTroupeService {
    private final ReviewTroupeRepository reviewTroupeRepository;
    private final UserRepository userRepository;
    private final TroupeMemberRepository troupeMemberRepository;
    public List<ReviewTroupe> retrieveAllReviewsTroupe(){
        log.info("Start - retrieveAllReviewsTroupe - args:none");
        val reviews = reviewTroupeRepository.findAll();
        log.info("End - retrieveAllReviewsTroupe - out: {}", reviews);
        return reviews;
    }

    public ReviewTroupe makeReviewTroupe(CreateReviewTroupeDto reviewTroupeDto){
        log.info("Start - makeReviewTroupe - args: {}", reviewTroupeDto);
        val user = userRepository.findById(reviewTroupeDto.getUserId()).orElseThrow(() -> new NotFoundException("no user found with id: "+ reviewTroupeDto.getUserId()));
        val member = troupeMemberRepository.findById(reviewTroupeDto.getMemberId()).orElseThrow(() -> new NotFoundException("no member found with id: "+ reviewTroupeDto.getMemberId()));
        val review = ReviewTroupe.builder()
                .review(reviewTroupeDto.getReview())
                .troupeMember(member)
                .user(user).build();
        log.info("End - makeReviewTroupe - out: {}", review);
        return review;
    }
}
