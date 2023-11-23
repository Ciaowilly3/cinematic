package com.cinematic.cinematic.repositories;

import com.cinematic.cinematic.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
