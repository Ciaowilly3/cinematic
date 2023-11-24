package com.cinematic.cinematic.services;

import com.cinematic.cinematic.models.Cinema;

import java.util.List;

public interface CinemaService {

    List<Cinema> retrieveAllCinema();

    Cinema addCinema(Cinema cinema);
}
