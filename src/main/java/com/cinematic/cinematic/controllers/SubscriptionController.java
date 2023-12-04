package com.cinematic.cinematic.controllers;

import com.cinematic.cinematic.dtos.SubscriptionDto;
import com.cinematic.cinematic.mappers.SubscriptionMapper;
import com.cinematic.cinematic.models.Film;
import com.cinematic.cinematic.models.Subscription;
import com.cinematic.cinematic.services.impl.SubscriptionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("subscriptions")
@RequiredArgsConstructor
@RestController
@Tag(name = "controller for subscription", description = "makes a bunch of staff for subscription")
public class SubscriptionController {
    private final SubscriptionServiceImpl subscriptionService;
    private final SubscriptionMapper subscriptionMapper;

    @Operation(summary = "Get a list of subscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list of subscription",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Subscription.class)) }),
            @ApiResponse(responseCode = "404", description = "subscription list not found",
                    content = @Content) })
    @GetMapping(path = "/{userId}")
    public SubscriptionDto retrieveSubByUserId(@PathVariable Long userId){
        return subscriptionMapper.toSubscriptionDto(subscriptionService.retrieveSubscriptionByUser(userId));
    }
}
