package com.cinematic.cinematic.repositories;

import com.cinematic.cinematic.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findAllByTitleContaining(String title);

    Optional<Film> findByTitle(String title);
}
