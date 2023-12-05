package com.cinematic.cinematic.services.impl;

import com.cleverbuilder.bookservice.Book;
import com.cleverbuilder.bookservice.GetAllBooksResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@Slf4j
class SoapServiceImplTest {

    @Mock
    private GetAllBooksResponse getAllBooksResponse;

    @InjectMocks
    private SoapServiceImpl soapService;
    @Test
    void getAllBooks() {
        GetAllBooksResponse expectedResponse = new GetAllBooksResponse();
        Book expectedBook = new Book();
        expectedBook.setTitle("Example Book");
        expectedResponse.getBook().add(expectedBook);

        val result = soapService.getAllBooks();
        result.getBook().add(expectedBook);

        assertEquals(expectedResponse.getBook(), result.getBook());
    }
}