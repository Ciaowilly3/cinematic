package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.CreateUserRequestDto;
import com.cinematic.cinematic.dtos.UserDto;
import com.cinematic.cinematic.mappers.UserMapper;
import com.cinematic.cinematic.models.User;
import com.cinematic.cinematic.repositories.UserRepository;
import com.cinematic.cinematic.security.JwtService;
import com.cinematic.cinematic.security.MyUserDetailsService;
import com.cinematic.cinematic.services.impl.UserServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
@ContextConfiguration
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ResourceLoader resourceLoader;
    @MockBean
    private UserServiceImpl userService;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MyUserDetailsService myUserDetailsService;
    @MockBean
    private JwtService jwtService;

    private final String path = "/users";
    @Test
    @WithMockUser
    void retrieveAllUsers() throws Exception{
        val user1 = User.builder().userName("marco").build();
        val user2 = User.builder().userName("luca").build();
        val userList = List.of(user1,user2);
        when(userService.retrieveAllUsers()).thenReturn(userList);
        val userDto1 = UserDto.builder().userName("marco").build();
        val userDto2 = UserDto.builder().userName("luca").build();
        val userDtoList = List.of(userDto1,userDto2);
        when(userMapper.toUserDtos(userList)).thenReturn(userDtoList);

        val resource = resourceLoader.getResource("classpath:users-list.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson, true));
        verify(userService, times(1)).retrieveAllUsers();
    }

    @Test
    @WithMockUser
    void retrieveUserById() throws Exception {
        val userId = 12L;
        val user = User.builder().userName("marco").userId(userId).build();
        when(userService.retrieveUserById(userId)).thenReturn(user);
        val userDto = UserDto.builder().userName("marco").build();
        when(userMapper.toUserDto(user)).thenReturn(userDto);


        val resource = resourceLoader.getResource("classpath:user-single.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);
        
        mockMvc.perform(get(path + "/single-user/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
        verify(userService, times(1)).retrieveUserById(userId);
    }

    @Test
    @WithMockUser
    void makeUser() throws Exception {
        val user = CreateUserRequestDto.builder().userName("marco").build();

        val resource = resourceLoader.getResource("classpath:user-single.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(post(path)
                    .contentType("application/json")
                        .with(csrf())
                    .content(expectedJson))
                    .andExpect(status().isCreated());

        verify(userService, times(1)).makeUser(user);
    }

    @Test
    @WithMockUser
    void updateUser() throws Exception {
        val user = CreateUserRequestDto.builder().userName("marco").build();
        val userId = 12L;

        val resource = resourceLoader.getResource("classpath:user-single.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(put(path + "/update-user/{id}", userId)
                        .contentType("application/json")
                        .with(csrf())
                        .content(expectedJson))
                .andExpect(status().isAccepted());

        verify(userService, times(1)).updateUser(user, userId);
    }

    @Test
    @WithMockUser
    void removeUser() throws Exception {
        val userId = 12L;
        val user = User.builder().userName("marco").userId(userId).build();
        when(userService.removeUser(userId)).thenReturn(user);
        val userDto = UserDto.builder().userName("marco").build();
        when(userMapper.toUserDto(user)).thenReturn(userDto);


        val resource = resourceLoader.getResource("classpath:user-single.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(delete(path + "/delete-user/{id}", userId).with(csrf()))
                .andExpect(status().isAccepted())
                .andExpect(content().json(expectedJson));
        verify(userService, times(1)).removeUser(userId);
    }
}