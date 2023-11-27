package com.cinematic.cinematic.services;

import com.cinematic.cinematic.dtos.CreateProgrammationRequestDto;
import com.cinematic.cinematic.models.Programmation;

import java.util.List;

public interface ProgrammationService {
    List<Programmation> retrieveAllProgrammations();

    Programmation makeProgrammation(CreateProgrammationRequestDto programmationRequestDto);

}
