package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.Subscription;
import com.cinematic.cinematic.models.User;
import com.cinematic.cinematic.repositories.SubscriptionRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class SubscriptionServiceImplTest {
    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;
    @Test
    void retrieveSubscriptionByUser() {
        val id = 12L;
        val user = User.builder().userName("marco").build();
        val sub = Subscription.builder().user(user).expiresAt(LocalDate.of(2023, 12, 4)).build();

        when(subscriptionRepository.findFirstByUserUserId(id)).thenReturn(Optional.of(sub));

        val result = subscriptionService.retrieveSubscriptionByUser(id);

        assertEquals(sub, result);
        verify(subscriptionRepository, times(1)).findFirstByUserUserId(id);
    }
}