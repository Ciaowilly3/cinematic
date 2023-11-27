package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.ReviewTroupe;
import com.cinematic.cinematic.repositories.ReviewTroupeRepository;
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

    public List<ReviewTroupe> retrieveAllReviewsTroupe(){
        log.info("Start - retrieveAllReviewsTroupe - args:none");
        val reviews = reviewTroupeRepository.findAll();
        log.info("End - retrieveAllReviewsTroupe - out: {}", reviews);
        return reviews;
    }
}
