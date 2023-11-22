package com.cinematic.cinematic.repositories;

import com.cinematic.cinematic.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findAllByTitleContaining(String title);

}
