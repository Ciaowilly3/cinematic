package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.models.Product;
import com.cinematic.cinematic.services.impl.SoapServiceImpl;
import com.cleverbuilder.bookservice.GetAllBooksResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(ExternalCallController.class)
@AutoConfigureMockMvc
class ExternalCallControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SoapServiceImpl soapService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private GetAllBooksResponse getAllBooksResponse;


    @Test
    void retrieveProductById() throws Exception {
        mockMvc.perform(get("/products/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void retrieveAllBooks() throws Exception {
        GetAllBooksResponse expectedResponse = new GetAllBooksResponse();

        Mockito.when(soapService.getAllBooks()).thenReturn(expectedResponse);

        mockMvc.perform(get("/products/books"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"book\": []}"));
    }
}
//todo: chiedere perchè non funzionano i test