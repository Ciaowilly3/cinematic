package com.cinematic.cinematic.repositories;

import com.cinematic.cinematic.models.FilmsGenres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmsGenresRepository extends JpaRepository<FilmsGenres, Long> {
    @Query("SELECT fg FROM FilmsGenres fg WHERE fg.film.id = :filmId")
    List<FilmsGenres> findAllByFilmIdCustomQuery(Long filmId);
}
