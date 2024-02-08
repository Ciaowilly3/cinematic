package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.models.FilmsGenres;
import com.cinematic.cinematic.models.Genre;
import com.cinematic.cinematic.repositories.FilmRepository;
import com.cinematic.cinematic.repositories.FilmsGenresRepository;
import com.cinematic.cinematic.repositories.GenreRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmsGenresServiceImplTest {

    @Mock
    private GenreRepository genreRepository;
    @Mock
    private FilmRepository filmRepository;

    @Mock
    private FilmsGenresRepository filmsGenresRepository;

    @InjectMocks
    private FilmsGenresServiceImpl filmsGenresService;
    @Test
    void addGenreToFilm() {
        val film = Film.builder().title("Rocky").build();
        when(filmRepository.findByTitle("Rocky")).thenReturn(Optional.ofNullable(film));

        val genre = Genre.builder().genreName("azione").build();

        when(genreRepository.findByGenreName("azione")).thenReturn(Optional.of(genre));

        filmsGenresService.addGenreToFilm("Rocky", "azione");

        val filmGenres = FilmsGenres.builder().film(film).genre(genre).build();

        verify(filmRepository, times(1)).findByTitle("Rocky");
        verify(genreRepository, times(1)).findByGenreName("azione");
        verify(filmsGenresRepository, times(1)).save(filmGenres);
    }

    @Test
    void deleteRelation() {
        val filmId =12L;
        filmsGenresService.deleteRelation(filmId);

        verify(filmsGenresRepository, times(1)).findAllByFilmIdCustomQuery(filmId);
    }
}