package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.requestdtos.FilmReviewRequestDto;
import com.cinematic.cinematic.dtos.requestdtos.UserRequestDto;
import com.cinematic.cinematic.models.User;
import com.cinematic.cinematic.services.impl.UserServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    private final String path = "/users";
    @Test
    void retrieveAllUsers() throws Exception{
        val user1 = User.builder().userName("marco").build();
        val user2 = User.builder().userName("luca").build();
        val userList = List.of(user1,user2);
        when(userService.retrieveAllUsers()).thenReturn(userList);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"userName\" : \"marco\"}, {\"userName\" : \"luca\"}]"));
        verify(userService, times(1)).retrieveAllUsers();
    }

    @Test
    void retrieveUserById() throws Exception {
        val userId = 12L;
        val user = User.builder().userName("marco").userId(userId).build();
        
        when(userService.retrieveUserById(userId)).thenReturn(user);
        
        mockMvc.perform(get(path + "/single-user/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"userName\" : \"marco\"}"));
        verify(userService, times(1)).retrieveUserById(userId);
    }

    @Test
    void makeUser() throws Exception {
        val user = UserRequestDto.builder().userName("nome").build();

        mockMvc.perform(post(path)
                    .contentType("application/json")
                    .content("{\"userName\" : \"nome\"}"))
                    .andExpect(status().isOk());

        verify(userService, times(1)).makeUser(user);
    }
}