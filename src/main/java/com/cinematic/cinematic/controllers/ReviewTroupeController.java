package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.CreateReviewTroupeDto;
import com.cinematic.cinematic.dtos.ReviewTroupeDto;
import com.cinematic.cinematic.mappers.ReviewTroupeMapper;
import com.cinematic.cinematic.services.impl.ReviewTroupeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<ReviewTroupeDto> makeReviewTroupe(@RequestBody CreateReviewTroupeDto reviewTroupeDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewTroupeMapper.toReviewTroupeDto(reviewTroupeService.makeReviewTroupe(reviewTroupeDto)));
    }
}
