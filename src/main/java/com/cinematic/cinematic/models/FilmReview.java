package com.cinematic.cinematic.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor
@Table(name = "film_reviews")
public class FilmReview {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filmReviewId;

    @Column(name = "review")
    private String review;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = true)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
}
