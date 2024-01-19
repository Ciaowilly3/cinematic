package com.cinematic.cinematic.services;

import com.cinematic.cinematic.dtos.PersonResponseDto;
import com.cinematic.cinematic.models.Product;
import com.cleverbuilder.bookservice.GetAllBooksResponse;

public interface ExternalService {
    GetAllBooksResponse invokeSoap();

    Product invokeRest(int id);

    PersonResponseDto invokeGrpc(String name, String surName);
}
