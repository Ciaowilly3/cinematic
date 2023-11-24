package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.UserDto;
import com.cinematic.cinematic.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "cinema", target = "cinemaDto")
    UserDto toUserDto(User user);

    List<UserDto> toUserDtos(List<User> users);

}
