package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.exceptions.NotFoundException;
import com.cinematic.cinematic.models.Genre;
import com.cinematic.cinematic.repositories.GenreRepository;
import com.cinematic.cinematic.services.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;


    @Override
    public List<Genre> retrieveAllGenres() {
        log.info("Start - retrieveAllgernes - args: none");
        val genreList = genreRepository.findAll();
        log.info("End - retrieveAllGenres out: {}", genreList.size());
        return genreList;
    }

    @Override
    public Genre retrieveGenreById(Long id) {
        log.info("Start - retrieveGenreById - args: id: {}", id);
        val genre = genreRepository.findById(id);
        log.info("End - retrieveGenreById - out: {}", genre.orElse(null));
        return genre.orElseThrow(() -> new NotFoundException("genre with id " + id + " not found"));
    }

    public Genre makeGenre(Genre genre){
        log.info("Start - makeGenre - args: genre: {}", genre);
        genreRepository.save(genre);
        log.info("End - retrieveGenreById - out: {}", genre);
        return genre;
    }
}
