package com.cinematic.cinematic.services;

import com.cinematic.cinematic.dtos.CreateUserRequestDto;
import com.cinematic.cinematic.models.User;

import java.util.List;

public interface UserService {

    List<User> retrieveAllUsers();

    User retrieveUserById(Long id);

    User makeUser(CreateUserRequestDto createUserRequestDto);
}
