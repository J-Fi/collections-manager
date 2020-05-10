package com.janflpk.collectionsmanager.service;

import com.janflpk.collectionsmanager.backend.domain.films.FilmDto;
import com.janflpk.collectionsmanager.backend.omdb.client.OmdbClient;
import com.janflpk.collectionsmanager.backend.service.OmdbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OmdbServiceTest {

    @Autowired
    private OmdbService omdbService;

    @MockBean
    private OmdbClient omdbClient;

    @Test
    public void shouldFetchFilmDto() {
        //Given
        FilmDto film1 = new FilmDto("FilmTitle1", "2000", "163", "DirectorName1",
                "Writers1", "Actors1", "Plot1", "language1", "country1",
                "posterLink1", "production1");

        when(omdbClient.getFilm("FilmTitle1")).thenReturn(film1);

        //When
        FilmDto filmDtoReturned = omdbService.getFilm("FilmTitle1");

        //Then
        Assert.assertNotNull(filmDtoReturned);
        Assert.assertEquals("FilmTitle1", filmDtoReturned.getFilmTitle());
        Assert.assertEquals("2000", filmDtoReturned.getYear());
    }
}
