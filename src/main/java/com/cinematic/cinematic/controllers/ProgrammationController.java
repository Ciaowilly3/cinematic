package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.CreateProgrammationRequestDto;
import com.cinematic.cinematic.dtos.ProgrammationDto;
import com.cinematic.cinematic.mappers.ProgrammationMapper;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.services.ProgrammationService;
import com.cinematic.cinematic.services.impl.ProgrammationServiceImpl;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/programmations")
@Tag(name = "controller for programmations", description = "makes a bunch of staff for programmations")
public class ProgrammationController {

    private final ProgrammationServiceImpl programmationService;

    private final ProgrammationMapper programmationMapper;

    @Operation(summary = "Get a list of programmation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list of programmations",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Film.class)) }),
            @ApiResponse(responseCode = "404", description = "programmations list not found",
                    content = @Content) })
    @GetMapping
    public List<ProgrammationDto> retrieveAllProgrammations(){
        return  programmationMapper.toProgrammationsDtos(programmationService.retrieveAllProgrammations());
    }

    @Operation(summary = "Make a programmations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "programmations made",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Film.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid programmations supplied",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<ProgrammationDto> makeProgrammation(@RequestBody CreateProgrammationRequestDto programmationRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(programmationMapper.toProgrammationDto(programmationService.makeProgrammation(programmationRequestDto)));
    }
}
