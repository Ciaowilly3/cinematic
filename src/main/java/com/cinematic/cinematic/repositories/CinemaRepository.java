package com.cinematic.cinematic.repositories;

import com.cinematic.cinematic.models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
