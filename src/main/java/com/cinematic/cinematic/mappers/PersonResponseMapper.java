package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.PersonResponseDto;
import com.cinematic.grpcperson.PersonResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonResponseMapper {
    PersonResponseDto toPersonResponseDto(PersonResponse personResponse);
}
