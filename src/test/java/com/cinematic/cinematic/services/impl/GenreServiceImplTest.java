package com.cinematic.cinematic.services.impl;

import com.cinematic.cinematic.models.Genre;
import com.cinematic.cinematic.models.TroupeMember;
import com.cinematic.cinematic.repositories.GenreRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class GenreServiceImplTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreServiceImpl genreService;
    @Test
    void retrieveGenreById() {
        val genreId = 12L;
        val genre = Genre.builder().genreId(genreId).genreName("marco").build();

        when(genreRepository.findById(genreId)).thenReturn(Optional.of(genre));

        val result = genreService.retrieveGenreById(genreId);

        assertEquals(genre, result);

        verify(genreRepository, times(1)).findById(genreId);
    }
}