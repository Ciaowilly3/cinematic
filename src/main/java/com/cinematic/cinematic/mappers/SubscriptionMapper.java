package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.SubscriptionDto;
import com.cinematic.cinematic.models.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(source = "user", target = "userDto")
    List<SubscriptionDto> toSubscriptionsDtos(List<Subscription> subscription);

    SubscriptionDto toSubscriptionDto(Subscription subscription);
}
