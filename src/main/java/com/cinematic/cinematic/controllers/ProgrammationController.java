package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.CreateProgrammationRequestDto;
import com.cinematic.cinematic.dtos.ProgrammationDto;
import com.cinematic.cinematic.mappers.ProgrammationMapper;
import com.cinematic.cinematic.services.ProgrammationService;
import com.cinematic.cinematic.services.impl.ProgrammationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/programmations")
public class ProgrammationController {

    private final ProgrammationServiceImpl programmationService;

    private final ProgrammationMapper programmationMapper;

    @GetMapping
    public List<ProgrammationDto> retrieveAllProgrammations(){
        return  programmationMapper.toProgrammationsDtos(programmationService.retrieveAllProgrammations());
    }

    @PostMapping
    public ResponseEntity<ProgrammationDto> makeProgrammation(@RequestBody CreateProgrammationRequestDto programmationRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(programmationMapper.toProgrammationDto(programmationService.makeProgrammation(programmationRequestDto)));
    }
}
