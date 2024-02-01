package com.cinematic.cinematic.services;

import com.cinematic.cinematic.models.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> retrieveAllGenres();
    Genre retrieveGenreById(Long id);

    Genre makeGenre(Genre genre);
}
