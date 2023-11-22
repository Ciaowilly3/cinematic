package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.UserDto;
import com.cinematic.cinematic.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "cinema", target = "cinemaDto")
    UserDto userToUserDto(User user);

    List<UserDto> usersToUserDtos(List<User> users);

}
