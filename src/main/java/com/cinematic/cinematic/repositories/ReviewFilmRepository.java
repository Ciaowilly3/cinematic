package com.cinematic.cinematic.repositories;

import com.cinematic.cinematic.models.ReviewFilm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewFilmRepository extends JpaRepository<ReviewFilm, Long> {
}
