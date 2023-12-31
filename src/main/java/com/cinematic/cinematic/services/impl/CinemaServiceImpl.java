package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.Cinema;
import com.cinematic.cinematic.repositories.CinemaRepository;
import com.cinematic.cinematic.services.CinemaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;


    public List<Cinema> retrieveAllCinema(){
        log.info("Start - retrieveAllCinema - args:none");
        val cinemaList = cinemaRepository.findAll();
        log.info("End - retrieveAllCinema - out: {}", cinemaList.size());
        return cinemaList;
    }

    public Cinema addCinema(Cinema cinema){
        log.info("Start - addCinema - args: cinema: {}", cinema);
        cinemaRepository.save(cinema);
        log.info("End - addCinema - out: {}", cinema);
        return  cinema;
    }
}
//TODO: Fare attenzione a quello che si stampa FATTO
//TODO: verificare se findAll può tornare null e gestire di conseguenza il size FATTO
//TODO: Assicurarsi che in tutti i servizi ci sia start end in ogni caso FATTO