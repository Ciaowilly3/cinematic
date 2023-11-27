package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.dtos.CreateProgrammationRequestDto;
import com.cinematic.cinematic.exceptions.NotFoundException;
import com.cinematic.cinematic.models.Programmation;
import com.cinematic.cinematic.repositories.CinemaRepository;
import com.cinematic.cinematic.repositories.FilmRepository;
import com.cinematic.cinematic.repositories.ProgrammationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProgrammationServiceImpl {

    private final ProgrammationRepository programmationRepository;
    private final FilmRepository filmRepository;
    private final CinemaRepository cinemaRepository;

    public List<Programmation> retrieveAllProgrammations(){
        log.info("Start - retrieveAllProgrammations - args:none");
        val programmations = programmationRepository.findAll();
        log.info("End - retrieveAllProgrammations - out: {}", programmations.size());
        return programmations;
    }

    public Programmation makeProgrammation(CreateProgrammationRequestDto programmationRequestDto){
        log.info("Start - makeProgrammation - args: programmation request: {}", programmationRequestDto);
        val programmation = Programmation.builder()
                .programmation(programmationRequestDto.getProgrammation())
                .film(filmRepository.findById(programmationRequestDto.getFilmId())
                        .orElseThrow(() -> new NotFoundException("not found the film with id " + programmationRequestDto.getFilmId())))
                .cinema(cinemaRepository.findById(programmationRequestDto.getCinemaId())
                        .orElseThrow(() -> new NotFoundException("not found the cinema with id " + programmationRequestDto.getCinemaId())))
                .build();
        programmationRepository.save(programmation);
        log.info("End - makeProgrammation - out: {}", programmation);
        return programmation;
    }
}
