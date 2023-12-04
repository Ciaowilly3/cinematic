package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.ReviewFilmDto;
import com.cinematic.cinematic.dtos.CreateFilmReviewRequestDto;
import com.cinematic.cinematic.mappers.ReviewFilmMapper;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.models.ReviewFilm;
import com.cinematic.cinematic.services.impl.ReviewFilmServiceImpl;
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

@RequestMapping("review-films")
@RequiredArgsConstructor
@Tag(name = "controller for reviewFilms", description = "makes a bunch of staff for reviewFilms")
@RestController

public class ReviewFilmController {

    private final ReviewFilmServiceImpl reviewFilmService;
    private final ReviewFilmMapper reviewFilmMapper;

    @Operation(summary = "Get a list of reviewfilms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list of reviewfilms",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewFilm.class)) }),
            @ApiResponse(responseCode = "404", description = "reviewFilm list not found",
                    content = @Content) })
    @GetMapping
    public List<ReviewFilmDto> retrieveAllFilmReviews(){
        return reviewFilmMapper.toFilmReviewsDtos(reviewFilmService.retrieveAllFilmReviews());
    }

    @Operation(summary = "Make a reviewfilm")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "reviewFilm made",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewFilm.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid reviewfilm supplied",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<ReviewFilmDto> makeFilmReview(@RequestBody CreateFilmReviewRequestDto requestDto){
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(reviewFilmMapper.ToFilmReviewDto(reviewFilmService.makeFilmReview(requestDto)));
    }
}
// TODO: rendere review entità principale cambiando nome FATTO
