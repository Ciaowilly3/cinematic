package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.PersonResponseDto;
import com.cinematic.cinematic.dtos.PersonRestRequest;
import com.cinematic.cinematic.models.Product;
import com.cinematic.cinematic.services.impl.ExternalServiceImpl;
import com.cleverbuilder.bookservice.GetAllBooksResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ExternalCallController {

    private final ExternalServiceImpl externalService;
    @GetMapping(path = "/{id}")
    public Product retrieveProductById(@PathVariable int id){
       return externalService.invokeRest(id);
    }

    @GetMapping(path = "/books")
    public GetAllBooksResponse retrieveAllBooks(){
        return externalService.invokeSoap();
    }

    @GetMapping(path = "/person")
    public ResponseEntity<PersonResponseDto> retrievePerson(@RequestBody PersonRestRequest personRestRequest){
        return ResponseEntity.ok().body(externalService.invokeGrpc(personRestRequest.getName(), personRestRequest.getSurName()));
    }
}
// todo: creare service dedicato che a sua volta invochi il rest template
// todo: aggiungere endpoint per grpc e