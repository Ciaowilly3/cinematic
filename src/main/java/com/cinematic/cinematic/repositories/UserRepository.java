package com.cinematic.cinematic.repositories;

import com.cinematic.cinematic.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
