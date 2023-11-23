package com.cinematic.cinematic.repositories;

import com.cinematic.cinematic.models.Film;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmRepositoryTest {

    @Mock
    private FilmRepository filmRepository;
    @Test
    void findAllByTitleContaining() {
        val film = Film.builder().title("Rocky").build();
        when(filmRepository.findAllByTitleContaining("Ro")).thenReturn(List.of(film));

        val result = filmRepository.findAllByTitleContaining("Ro");

        assertEquals(film, result.get(0));
    }
}
//TODO: eliminare