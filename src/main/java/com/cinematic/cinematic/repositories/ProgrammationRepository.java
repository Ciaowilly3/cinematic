package com.cinematic.cinematic.repositories;

import com.cinematic.cinematic.models.Programmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammationRepository extends JpaRepository<Programmation, Long> {
}
