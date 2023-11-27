package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.ReviewTroupeDto;
import com.cinematic.cinematic.mappers.ReviewTroupeMapper;
import com.cinematic.cinematic.services.impl.ReviewTroupeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("review-troupes")
@RequiredArgsConstructor
@RestController
public class ReviewTroupeController {
    private final ReviewTroupeServiceImpl reviewTroupeService;
    private final ReviewTroupeMapper reviewTroupeMapper;
    @GetMapping
    public List<ReviewTroupeDto> retrieveAllReviewsTroupe(){
        return  reviewTroupeMapper.toReviewTroupeDtos(reviewTroupeService.retrieveAllReviewsTroupe());
    }
}
