package com.cinematic.cinematic.repositories;

import com.cinematic.cinematic.models.FilmsGenres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmsGenresRepository extends JpaRepository<FilmsGenres, Long> {
}
