package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.CinemaDto;
import com.cinematic.cinematic.mappers.CinemaMapper;
import com.cinematic.cinematic.models.Cinema;
import com.cinematic.cinematic.services.impl.CinemaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("cinema")
@RequiredArgsConstructor
@RestController
public class CinemaController {

    private final CinemaServiceImpl cinemaService;
    private final CinemaMapper cinemaMapper;

    @GetMapping
    public List<CinemaDto> retrieveAllCinema(){
        return cinemaMapper.toCinemaDtos(cinemaService.retrieveAllCinema());
    }

    @PostMapping
    public void addCinema(@RequestBody Cinema cinema){cinemaService.addCinema(cinema);}
}
