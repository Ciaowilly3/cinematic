package com.cinematic.cinematic.repositories;

import com.cinematic.cinematic.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByGenreName (String genreName);
}
