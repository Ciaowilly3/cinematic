package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.SubscriptionDto;
import com.cinematic.cinematic.mappers.SubscriptionMapper;
import com.cinematic.cinematic.services.impl.SubscriptionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("subscriptions")
@RequiredArgsConstructor
@RestController
public class SubscriptionController {
    private final SubscriptionServiceImpl subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    @GetMapping(path = "/{userId}")
    public SubscriptionDto retrieveSubByUserId(@PathVariable Long userId){
        return subscriptionMapper.toSubscriptionDto(subscriptionService.retrieveSubscriptionByUser(userId));
    }
}
