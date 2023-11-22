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
@Table(name = "films")
public class Film {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filmId;

    @Column(name = "cover_img")
    private String coverImg;

    @Column(name = "title")
    private String title;

    @Column(name = "nation_of_production")
    private String nationOfProduction;

    @Column(name = "plot")
    private String plot;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "fun_facts")
    private String funFacts;

}
