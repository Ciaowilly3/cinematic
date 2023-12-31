package com.cinematic.cinematic.services;

import com.cinematic.cinematic.dtos.CreateReviewTroupeDto;
import com.cinematic.cinematic.models.ReviewTroupe;

import java.util.List;

public interface ReviewTroupeService {

    List<ReviewTroupe> retrieveAllReviewsTroupe();

    ReviewTroupe makeReviewTroupe(CreateReviewTroupeDto reviewTroupeDto);
}
