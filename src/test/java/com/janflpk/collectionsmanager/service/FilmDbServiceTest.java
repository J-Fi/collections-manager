package com.janflpk.collectionsmanager.service;

import com.janflpk.collectionsmanager.backend.domain.films.Film;
import com.janflpk.collectionsmanager.backend.domain.films.FilmsCollection;
import com.janflpk.collectionsmanager.backend.domain.user.User;
import com.janflpk.collectionsmanager.backend.repository.FilmRepository;
import com.janflpk.collectionsmanager.backend.service.FilmDbService;
import com.janflpk.collectionsmanager.backend.service.FilmsCollectionDbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmDbServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilmDbServiceTest.class);

    @Autowired
    private FilmDbService filmDbService;

    @MockBean
    private FilmRepository filmRepository;

    @MockBean
    private FilmsCollectionDbService filmsCollectionDbService;

    @Test
    public void shouldFetchAllFilmsByText() {
        //Given
        List<Film> filmList = new ArrayList<>();
        User user = new User(1L, "userFirstName", "userLastName", "birthsday", "email", "login", "password");
        FilmsCollection filmsCollection = new FilmsCollection(1L, "My Films", user, filmList);

        Film film1 = new Film(1L, "FilmTitle1", "2000", "163", "DirectorName1",
                "Writers1", "Actors1", "Plot1", "language1", "country1",
                "posterLink1", "production1", filmsCollection);
        Film film2 = new Film("FilmTitle2", "2002", "165", "DirectorName2",
                "Writers2", "Actors2", "Plot2", "language2", "country2",
                "posterLink2", "production2", filmsCollection);

        filmList.add(film1);
        filmList.add(film2);

        when(filmRepository.findAll("Title")).thenReturn(filmList);

        //When
        List<Film> filmListReturned = filmDbService.findAll("Title");

        //Then
        Assert.assertEquals(2, filmListReturned.size());
    }

    @Test
    public void shouldFetchFilmById() {
        //Given
       Film film1 = new Film("FilmTitle1", "2000", "163", "DirectorName1",
                "Writers1", "Actors1", "Plot1", "language1", "country1",
                "posterLink1", "production1");
        film1.setFilmId(1L);

        when(filmRepository.findById(1L)).thenReturn(Optional.of(film1));

        //When
        Film filmReturned = filmDbService.findById(1L);

        //Then
        Assert.assertNotNull(filmReturned);
        Assert.assertEquals(Long.valueOf(1L), filmReturned.getFilmId());
        Assert.assertEquals("FilmTitle1", filmReturned.getFilmTitle());
        Assert.assertEquals("2000", filmReturned.getYear());
        Assert.assertEquals("163", filmReturned.getRuntime());
        Assert.assertEquals("DirectorName1", filmReturned.getDirectorName());
        Assert.assertEquals("Writers1", filmReturned.getWriters());
        Assert.assertEquals("Actors1", filmReturned.getActors());
        Assert.assertEquals("Plot1", filmReturned.getPlot());
        Assert.assertEquals("language1", filmReturned.getLanguage());
        Assert.assertEquals("country1", filmReturned.getCountry());
        Assert.assertEquals("posterLink1", filmReturned.getPosterLink());
        Assert.assertEquals("production1", filmReturned.getProduction());
    }

    @Test
    public void shouldFetchAllFilmsContainingSpecificStringTest() {
        //Given
        List<Film> films = new ArrayList<>();
        FilmsCollection filmsCollection = new FilmsCollection(1L, "MyFilms");

        Film film1 = new Film("FilmTitle1", "2000", "163", "DirectorName1",
                "Writers1", "Actors1", "Plot1", "language1", "country1",
                "posterLink1", "production1");
        Film film2 = new Film("FilmTitle2", "2002", "165", "DirectorName2",
                "Writers2", "Actors2", "Plot2", "language2", "country2",
                "posterLink2", "production2");

        film1.setFilmsCollection(filmsCollection);
        film2.setFilmsCollection(filmsCollection);

        films.add(film1);
        films.add(film2);

        when(filmRepository.findAll()).thenReturn(films);

        //When
        List<Film> returnedList = filmDbService.fetchFilmsByFilmsCollectionId(filmsCollection.getFilmsCollectionId());

        //Then
        Assert.assertEquals(2, returnedList.size());
    }

    @Test
    public void shouldUpdateFilm() {
        //Given
        List<Film> films = new ArrayList<>();
        FilmsCollection filmsCollection = new FilmsCollection(1L, "MyFilms");

        Film film1 = new Film("FilmTitle1", "2000", "163", "DirectorName1",
                "Writers1", "Actors1", "Plot1", "language1", "country1",
                "posterLink1", "production1");
        Film film2 = new Film("FilmTitle2", "2002", "165", "DirectorName2",
                "Writers2", "Actors2", "Plot2", "language2", "country2",
                "posterLink2", "production2");
        Film film1Updated = new Film("FilmTitle1Up", "2002", "165", "DirectorName2",
                "Writers2", "Actors2", "Plot2", "language2", "country2",
                "posterLink2", "production2");

        film1.setFilmsCollection(filmsCollection);
        film2.setFilmsCollection(filmsCollection);
        film1Updated.setFilmsCollection(filmsCollection);

        film1.setFilmId(1L);
        film2.setFilmId(2L);
        film1Updated.setFilmId(film1.getFilmId());

        films.add(film1);
        films.add(film2);

        when(filmRepository.findAll()).thenReturn(films);
        when(filmRepository.save(film1)).thenReturn(film1Updated);

        //When
        LOGGER.info("films.size() = " + filmRepository.findAll().get(0) + " " + filmRepository.findAll().get(1));
        LOGGER.info("film1Updated id = " + filmRepository.save(film1).getFilmId());
        Film filmReturned = filmDbService.updateFilm(1L, 1L, film1);

        //Then
        Assert.assertEquals(Long.valueOf(1L), filmReturned.getFilmId());
        Assert.assertEquals("FilmTitle1Up", filmReturned.getFilmTitle());
    }

    @Test
    public void shouldSaveFilmTest() {
        //Given
        Film film1 = new Film("FilmTitle1", "2000", "163", "DirectorName1",
                "Writers1", "Actors1", "Plot1", "language1", "country1",
                "posterLink1", "production1");

        FilmsCollection filmsCollection = new FilmsCollection(1L, "MyFilms");

        when(filmRepository.save(film1)).thenReturn(film1);
        when(filmsCollectionDbService.findById(1L)).thenReturn(filmsCollection);

        //When
        Film returnedFilm = filmDbService.saveFilm(film1, 1L);

        //Then
        Assert.assertNotNull(returnedFilm);
        Assert.assertEquals("FilmTitle1", returnedFilm.getFilmTitle());
        Assert.assertEquals(Long.valueOf(1L), returnedFilm.getFilmsCollection().getFilmsCollectionId());
    }

    @Test
    public void shouldDeleteFilmTest() {
        //Given
        FilmsCollection fc = new FilmsCollection(1L, "MyFilms");
        Film film1 = new Film("FilmTitle1", "2000", "163", "DirectorName1",
                "Writers1", "Actors1", "Plot1", "language1", "country1",
                "posterLink1", "production1", fc);
        film1.setFilmId(1l);
        //When
        filmDbService.deleteFilm(1L);

        //Then
        verify(filmRepository, times(1)).deleteById(eq(film1.getFilmId()));
    }
}
