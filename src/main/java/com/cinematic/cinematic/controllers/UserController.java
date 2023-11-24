package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.UserDto;
import com.cinematic.cinematic.dtos.requestdtos.UserRequestDto;
import com.cinematic.cinematic.mappers.UserMapper;
import com.cinematic.cinematic.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> retrieveAllUsers(){return userMapper.usersToUserDtos(userService.retrieveAllUsers());
    }

    @GetMapping(path = "singleUser/{id}")
    public UserDto retrieveUserById(@PathVariable Long id){return userMapper.userToUserDto(userService.retrieveUserById(id));}

    @PostMapping
    public void makeUser(@RequestBody UserRequestDto userRequestDto){
        userService.makeUser(userRequestDto);
    }
}
//TODO: utilizzare ResponseEntity<x> come tipo di uscita
