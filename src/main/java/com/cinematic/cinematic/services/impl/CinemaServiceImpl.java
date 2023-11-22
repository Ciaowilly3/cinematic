package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.Cinema;
import com.cinematic.cinematic.repositories.CinemaRepository;
import com.cinematic.cinematic.services.CinemaService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;

    @Autowired
    public CinemaServiceImpl (CinemaRepository cinemaRepository){this.cinemaRepository = cinemaRepository;}

    public List<Cinema> retrieveAllCinema(){
        log.info("Start - retrieveAllCinema - args:none");
        val cinemaList = cinemaRepository.findAll();
        log.info("End - retrieveAllCinema - out: {}", cinemaList);
        return cinemaList;
    }

    public void addCinema(Cinema cinema){
        log.info("Start - addCinema - args: cinema: {}", cinema);
        cinemaRepository.save(cinema);
    }
}
