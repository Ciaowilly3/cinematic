package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.services.SoapService;
import com.cleverbuilder.bookservice.Book;
import com.cleverbuilder.bookservice.GetAllBooksResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SoapServiceImpl implements SoapService {

    public GetAllBooksResponse getAllBooks() {
        GetAllBooksResponse response = new GetAllBooksResponse();
        response.getBook();
        return response;
    }
}
