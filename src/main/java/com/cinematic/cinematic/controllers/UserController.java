package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.UserDto;
import com.cinematic.cinematic.dtos.CreateUserRequestDto;
import com.cinematic.cinematic.mappers.UserMapper;
import com.cinematic.cinematic.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserDto> makeUser(@RequestBody CreateUserRequestDto createUserRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toUserDto(userService.makeUser(createUserRequestDto)));
    }

    @PutMapping(path = "update-user/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody CreateUserRequestDto userRequestDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(userMapper.toUserDto(userService.updateUser(userRequestDto, id)));
    }

    @DeleteMapping(path = "delete-user/{id}")
    public ResponseEntity<UserDto> removeUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(userMapper.toUserDto(userService.removeUser(id)));
    }
}
//TODO: utilizzare ResponseEntity<x> come tipo di uscita FATTO
