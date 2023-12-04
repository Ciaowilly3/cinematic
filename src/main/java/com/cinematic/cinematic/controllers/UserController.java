package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.UserDto;
import com.cinematic.cinematic.dtos.CreateUserRequestDto;
import com.cinematic.cinematic.mappers.UserMapper;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.models.User;
import com.cinematic.cinematic.services.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("users")
@RestController
@RequiredArgsConstructor
@Tag(name = "controller for users", description = "makes a bunch of staff for users")
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;
    @Operation(summary = "Get a list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list of users",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "users list not found",
                    content = @Content) })
    @GetMapping
    public List<UserDto> retrieveAllUsers(){
        return userMapper.toUserDtos(userService.retrieveAllUsers());
    }

    @Operation(summary = "Get a user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "user not found",
                    content = @Content) })
    @GetMapping(path = "single-user/{id}")
    public UserDto retrieveUserById(@PathVariable Long id){return userMapper.toUserDto(userService.retrieveUserById(id));}

    @Operation(summary = "Make a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "user made",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid user supplied",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<UserDto> makeUser(@RequestBody CreateUserRequestDto createUserRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toUserDto(userService.makeUser(createUserRequestDto)));
    }
    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "user not found",
                    content = @Content) })
    @PutMapping(path = "update-user/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody CreateUserRequestDto userRequestDto){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(userMapper.toUserDto(userService.updateUser(userRequestDto, id)));
    }
    @Operation(summary = "Delete a user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Deleted the user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "user not found",
                    content = @Content) })
    @DeleteMapping(path = "delete-user/{id}")
    public ResponseEntity<UserDto> removeUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(userMapper.toUserDto(userService.removeUser(id)));
    }
}
//TODO: utilizzare ResponseEntity<x> come tipo di uscita FATTO
