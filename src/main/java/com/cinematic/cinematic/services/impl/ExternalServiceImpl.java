package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.client.PersonClient;
import com.cinematic.cinematic.dtos.PersonResponseDto;
import com.cinematic.cinematic.mappers.PersonResponseMapper;
import com.cinematic.cinematic.models.Product;
import com.cinematic.cinematic.services.ExternalService;
import com.cleverbuilder.bookservice.GetAllBooksResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExternalServiceImpl implements ExternalService {

//    private final GetAllBooks book;
    private final PersonClient personClient;
    private final PersonResponseMapper personResponseMapper;
    public GetAllBooksResponse invokeSoap() {
        log.info("Start - invokeSoap - args: none");
//        val result = book.;
        return null;
    }

    public Product invokeRest(int id){
        log.info("Start - invokeRest - args: id: {}", id);
        val restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://dummyjson.com/products/" + id, Product.class);
    }

    public PersonResponseDto invokeGrpc(String name, String surname){
        return personResponseMapper.toPersonResponseDto(personClient.getPerson(name, surname));
    }


}
