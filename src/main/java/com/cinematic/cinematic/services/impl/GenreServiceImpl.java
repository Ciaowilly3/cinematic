package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.dtos.GenreDto;
import com.cinematic.cinematic.exceptions.NotFoundException;
import com.cinematic.cinematic.models.Genre;
import com.cinematic.cinematic.repositories.GenreRepository;
import com.cinematic.cinematic.services.GenreService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository){this.genreRepository = genreRepository;}

    @Override
    public Genre retrieveGenreById(Long id) {
        log.info("Start - retrieveGenreById - args: id: {}", id);
        val genre = genreRepository.findById(id);
        if (genre.isEmpty()){
            throw new NotFoundException("genre with id " + id + " not found");
        }
        log.info("Start - retrieveGenreById - args: id: {}", genre.get());
        return genre.get();
    }
}
