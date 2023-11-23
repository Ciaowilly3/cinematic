package com.cinematic.cinematic.repositories;

import com.cinematic.cinematic.models.FilmReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmReviewRepository extends JpaRepository<FilmReview, Long> {
}
