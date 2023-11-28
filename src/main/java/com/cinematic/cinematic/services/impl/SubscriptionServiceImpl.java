package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.dtos.SubscriptionDto;
import com.cinematic.cinematic.exceptions.NotFoundException;
import com.cinematic.cinematic.models.Subscription;
import com.cinematic.cinematic.repositories.SubscriptionRepository;
import com.cinematic.cinematic.services.SubscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public Subscription retrieveSubscriptionByUser(Long userId){
        log.info("Start - retrieveSubByUser - args: userId: {}", userId);
        val sub = subscriptionRepository.findFirstByUserUserId(userId).orElseThrow(() -> new NotFoundException("the user with id " + userId + " has got no Sub"));
        if (sub.getExpiresAt().isBefore(LocalDate.now())){
            sub.setExpired(true);
        }
        log.info("Start - retrieveSubByUser - out: {}", sub);
        return sub;
    }

}
