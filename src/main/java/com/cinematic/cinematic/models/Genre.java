package com.cinematic.cinematic.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long genreId;

    @Column(name = "genre_name")
    private String genreName;
//
//    @OneToMany(mappedBy = "genre")
//    private Set<FilmsGenres> filmGenre;
}
