package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.ProgrammationDto;
import com.cinematic.cinematic.models.Programmation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProgrammationMapper {
    ProgrammationDto toProgrammationDto(Programmation programmation);

    List<ProgrammationDto> toProgrammationsDtos(List<Programmation> programmations);
}
