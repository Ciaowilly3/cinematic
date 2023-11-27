package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.ProgrammationDto;
import com.cinematic.cinematic.models.Programmation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProgrammationMapper {

    @Mapping(source = "film", target = "filmDto")
    @Mapping(source = "cinema", target = "cinemaDto")
    ProgrammationDto toProgrammationDto(Programmation programmation);

    List<ProgrammationDto> toProgrammationsDtos(List<Programmation> programmations);
}
