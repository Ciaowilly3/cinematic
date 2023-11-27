package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.Programmation;
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

    public List<Programmation> retrieveAllProgrammations(){
        log.info("Start - retrieveAllProgrammations - args:none");
        val programmations = programmationRepository.findAll();
        log.info("End - retrieveAllProgrammations - out: {}", programmations.size());
        return programmations;
    }
}
