package com.cinematic.cinematic.mappers;

import com.cinematic.cinematic.dtos.ReviewTroupeDto;
import com.cinematic.cinematic.models.ReviewTroupe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewTroupeMapper {
    @Mapping(source = "troupeMember", target = "troupeMemberDto")
    @Mapping(source = "user", target = "userDto")
    ReviewTroupeDto toReviewTroupeDto(ReviewTroupe reviewTroupe);
    List<ReviewTroupeDto> toReviewTroupeDtos(List<ReviewTroupe> reviewTroupes);
}
