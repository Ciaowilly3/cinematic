package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.CreateReviewTroupeDto;
import com.cinematic.cinematic.dtos.ReviewTroupeDto;
import com.cinematic.cinematic.mappers.ReviewTroupeMapper;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.models.ReviewTroupe;
import com.cinematic.cinematic.services.impl.ReviewTroupeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("review-troupes")
@RequiredArgsConstructor
@RestController
@Tag(name = "controller for reviewTroupe", description = "makes a bunch of staff for reviewTroupe")
public class ReviewTroupeController {
    private final ReviewTroupeServiceImpl reviewTroupeService;
    private final ReviewTroupeMapper reviewTroupeMapper;

    @Operation(summary = "Get a list of reviewTroupe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list of reviewTroupe",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewTroupe.class)) }),
            @ApiResponse(responseCode = "404", description = "reviewTroupe list not found",
                    content = @Content) })
    @GetMapping
    public List<ReviewTroupeDto> retrieveAllReviewsTroupe(){
        return  reviewTroupeMapper.toReviewTroupeDtos(reviewTroupeService.retrieveAllReviewsTroupe());
    }
    @Operation(summary = "Make a reviewTroupe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "reviewTroupe made",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewTroupe.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid reviewTroupe supplied",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<ReviewTroupeDto> makeReviewTroupe(@RequestBody CreateReviewTroupeDto reviewTroupeDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewTroupeMapper.toReviewTroupeDto(reviewTroupeService.makeReviewTroupe(reviewTroupeDto)));
    }
}
