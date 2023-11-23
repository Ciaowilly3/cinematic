package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.UserDto;
import com.cinematic.cinematic.dtos.requestdtos.UserRequestDto;
import com.cinematic.cinematic.mappers.UserMapper;
import com.cinematic.cinematic.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("users")
@RestController
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService){this.userService = userService;}

    @GetMapping
    public List<UserDto> retrieveAllUsers(){return UserMapper.INSTANCE.usersToUserDtos(userService.retrieveAllUsers());
    }

    @GetMapping(path = "singleUser/{id}")
    public UserDto retrieveUserById(@PathVariable("id") Long id){return UserMapper.INSTANCE.userToUserDto(userService.retrieveUserById(id));}

    @PostMapping
    public void makeUser(@RequestBody UserRequestDto userRequestDto){
        userService.makeUser(userRequestDto);
    }
}
//TODO: utilizzare ResponseEntity<x> come tipo di uscita
