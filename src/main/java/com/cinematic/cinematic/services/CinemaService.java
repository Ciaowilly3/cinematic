package com.cinematic.cinematic.services;

import com.cinematic.cinematic.dtos.CinemaDto;
import com.cinematic.cinematic.models.Cinema;

import java.util.List;

public interface CinemaService {

    public List<Cinema> retrieveAllCinema();

    public void addCinema(Cinema cinema);
}
