package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.models.Product;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("prducts")
public class ExternalCallController {

    @GetMapping(path = "/{id}")
    public Product retrieveProductById(@PathVariable int id){
        val restTemplate = new RestTemplate();

        return restTemplate.getForObject("https://dummyjson.com/products/" + id, Product.class);
    }
}
