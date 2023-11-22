package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.CinemaDto;
import com.cinematic.cinematic.mappers.CinemaMapper;
import com.cinematic.cinematic.models.Cinema;
import com.cinematic.cinematic.services.impl.CinemaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/cinema")
@RestController
public class CinemaController {

    private final CinemaServiceImpl cinemaService;

    @Autowired
    public CinemaController(CinemaServiceImpl cinemaService){this.cinemaService = cinemaService;}

    @GetMapping
    public List<CinemaDto> retrieveAllCinema(){
        return CinemaMapper.INSTANCE.cinemaToCinemaDtos(cinemaService.retrieveAllCinema());
    }

    @PostMapping
    public void addCinema(@RequestBody Cinema cinema){cinemaService.addCinema(cinema);}
}
