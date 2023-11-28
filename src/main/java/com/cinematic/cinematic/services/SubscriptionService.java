package com.cinematic.cinematic.services;

import com.cinematic.cinematic.dtos.SubscriptionDto;
import com.cinematic.cinematic.models.Subscription;

public interface SubscriptionService {

    Subscription retrieveSubscriptionByUser(Long userId);
}
