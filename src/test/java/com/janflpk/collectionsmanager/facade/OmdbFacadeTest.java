package com.janflpk.collectionsmanager.facade;

import com.janflpk.collectionsmanager.backend.domain.films.Film;
import com.janflpk.collectionsmanager.backend.domain.films.FilmDto;
import com.janflpk.collectionsmanager.backend.omdb.facade.OmdbFacade;
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
public class OmdbFacadeTest {

    @Autowired
    private OmdbFacade omdbFacade;

    @MockBean
    private OmdbService omdbService;

    @Test
    public void shouldFetchFilm() {
        //Given
        FilmDto filmDto = new FilmDto("FilmTitle", "2000", "163", "DirectorName",
                "Writers", "Actors", "Plot", "language", "country",
                "posterLink", "production");

        when(omdbService.getFilm("FilmTitle")).thenReturn(filmDto);

        //When
        Film film = omdbFacade.getFilm("FilmTitle");

        //Then
        Assert.assertNotNull(film);
        Assert.assertEquals("FilmTitle", film.getFilmTitle());
        Assert.assertEquals("2000", film.getYear());
        Assert.assertEquals("163", film.getRuntime());
        Assert.assertEquals("DirectorName", film.getDirectorName());
        Assert.assertEquals("Writers", film.getWriters());
        Assert.assertEquals("Actors", film.getActors());
        Assert.assertEquals("Plot", film.getPlot());
        Assert.assertEquals("language", film.getLanguage());
        Assert.assertEquals("country", film.getCountry());
        Assert.assertEquals("posterLink", film.getPosterLink());
        Assert.assertEquals("production", film.getProduction());
    }
}
