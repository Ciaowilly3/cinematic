package com.cinematic.cinematic.repositories;

import com.cinematic.cinematic.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findFirstByUserUserId(Long userId);
}
