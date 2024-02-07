package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.exceptions.NotFoundException;
import com.cinematic.cinematic.models.FilmsGenres;
import com.cinematic.cinematic.repositories.FilmRepository;
import com.cinematic.cinematic.repositories.FilmsGenresRepository;
import com.cinematic.cinematic.repositories.GenreRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilmsGenresServiceImpl {

    private final FilmsGenresRepository filmsGenresRepository;
    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;

    @Transactional
    public void addGenreToFilm(String filmTitle, String genreName) {
        log.info("Start - addGenreToFilm - args: filmTitle: {}, genreName: {}", filmTitle, genreName);

        val film = filmRepository.findByTitle(filmTitle)
                .orElseThrow(() -> new NotFoundException("Film with title " + filmTitle + " not found"));

        val genre = genreRepository.findByGenreName(genreName)
                .orElseThrow(() -> new NotFoundException("Genre with name " + genreName + " not found"));

        val filmsGenresToSave = new FilmsGenres();
        filmsGenresToSave.setFilm(film);
        filmsGenresToSave.setGenre(genre);
        filmsGenresRepository.save(filmsGenresToSave);

        log.info("End - addGenreToFilm");
    }
}
