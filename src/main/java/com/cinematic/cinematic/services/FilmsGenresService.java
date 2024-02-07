package com.cinematic.cinematic.services;

public interface FilmsGenresService {

    void addGenreToFilm(String filmTitle, String genreName);

    void deleteRelation(Long filmId);
}
