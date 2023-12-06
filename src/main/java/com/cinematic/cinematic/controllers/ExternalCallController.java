package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.models.Product;
import com.cinematic.cinematic.services.impl.SoapServiceImpl;
import com.cleverbuilder.bookservice.GetAllBooksResponse;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ExternalCallController {

    private final SoapServiceImpl soapService;
    @GetMapping(path = "/{id}")
    public Product retrieveProductById(@PathVariable int id){
        val restTemplate = new RestTemplate();

        return restTemplate.getForObject("https://dummyjson.com/products/" + id, Product.class);
    }

    @GetMapping(path = "/books")
    public GetAllBooksResponse retrieveAllBooks(){
        return soapService.getAllBooks();
    }
}
