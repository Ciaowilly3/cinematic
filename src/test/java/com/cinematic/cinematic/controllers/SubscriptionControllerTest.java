package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.SubscriptionDto;
import com.cinematic.cinematic.dtos.UserDto;
import com.cinematic.cinematic.mappers.ReviewTroupeMapper;
import com.cinematic.cinematic.mappers.SubscriptionMapper;
import com.cinematic.cinematic.models.Subscription;
import com.cinematic.cinematic.models.User;
import com.cinematic.cinematic.services.impl.ReviewTroupeServiceImpl;
import com.cinematic.cinematic.services.impl.SubscriptionServiceImpl;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubscriptionController.class)
@AutoConfigureMockMvc
class SubscriptionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ResourceLoader resourceLoader;
    @MockBean
    private SubscriptionServiceImpl subscriptionService;
    @MockBean
    private SubscriptionMapper subscriptionMapper;

    private final String path = "/subscriptions";
    @Test
    void retrieveSubByUserId() throws Exception {
        val id = 12L;
        val user = User.builder().userName("marco").build();
        val sub = Subscription.builder().user(user).build();
        when(subscriptionService.retrieveSubscriptionByUser(id)).thenReturn(sub);
        val userDto = UserDto.builder().userName("marco").build();
        val subDto = SubscriptionDto.builder().userDto(userDto).build();
        when(subscriptionMapper.toSubscriptionDto(sub)).thenReturn(subDto);


        val resource = resourceLoader.getResource("classpath:subscription-single.json");
        val expectedJson = new String(Objects.requireNonNull(resource.getInputStream()).readAllBytes(), StandardCharsets.UTF_8);

        mockMvc.perform(get(path + "/{userId}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

        verify(subscriptionService, times(1)).retrieveSubscriptionByUser(id);
    }
}