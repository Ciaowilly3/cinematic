package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.repositories.UserRepository;
import com.cinematic.cinematic.security.JwtService;
import com.cinematic.cinematic.security.MyUserDetailsService;
import com.cinematic.cinematic.services.impl.ExternalServiceImpl;
import com.cleverbuilder.bookservice.GetAllBooksResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(ExternalCallController.class)
@AutoConfigureMockMvc
@ContextConfiguration
class ExternalCallControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExternalServiceImpl soapService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private GetAllBooksResponse getAllBooksResponse;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private MyUserDetailsService myUserDetailsService;
    @MockBean
    private JwtService jwtService;


    @Test
    @WithMockUser
    void retrieveProductById() throws Exception {
        mockMvc.perform(get("/products/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void retrieveAllBooks() throws Exception {
//        GetAllBooksResponse expectedResponse = new GetAllBooksResponse();
//
//        Mockito.when(soapService.getAllBooks()).thenReturn(expectedResponse);
//
//        mockMvc.perform(get("/products/books"))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{\"book\": []}"));
    }
}
