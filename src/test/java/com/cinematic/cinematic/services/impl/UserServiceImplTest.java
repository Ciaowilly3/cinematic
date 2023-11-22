package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.User;
import com.cinematic.cinematic.repositories.UserRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
    @Test
    void retrieveAllUsers() {
        val user1 = User.builder().userName("marco").build();
        val user2 = User.builder().userName("luca").build();
        val userList = List.of(user1,user2);

        when(userRepository.findAll()).thenReturn(userList);

        val result = userService.retrieveAllUsers();

        assertEquals(userList, result);

    }

    @Test
    void retrieveUserById() {
        val userID = 12L;
        val user = User.builder().userName("marco").userId(userID).build();

        when(userRepository.findById(userID)).thenReturn(Optional.of(user));

        val result = userService.retrieveUserById(userID);

        assertEquals(user, result);
    }
}