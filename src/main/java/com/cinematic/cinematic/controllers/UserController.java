package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.UserDto;
import com.cinematic.cinematic.dtos.CreateUserRequestDto;
import com.cinematic.cinematic.mappers.UserMapper;
import com.cinematic.cinematic.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> retrieveAllUsers(){return userMapper.toUserDtos(userService.retrieveAllUsers());
    }

    @GetMapping(path = "single-user/{id}")
    public UserDto retrieveUserById(@PathVariable Long id){return userMapper.toUserDto(userService.retrieveUserById(id));}

    @PostMapping
    public void makeUser(@RequestBody CreateUserRequestDto createUserRequestDto){
        userService.makeUser(createUserRequestDto);
    }
}
//TODO: utilizzare ResponseEntity<x> come tipo di uscita
