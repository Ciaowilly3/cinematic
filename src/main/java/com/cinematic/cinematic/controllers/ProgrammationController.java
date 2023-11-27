package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.ProgrammationDto;
import com.cinematic.cinematic.mappers.ProgrammationMapper;
import com.cinematic.cinematic.services.impl.ProgrammationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
