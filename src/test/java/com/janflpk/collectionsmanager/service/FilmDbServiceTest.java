package com.janflpk.collectionsmanager.service;

import com.janflpk.collectionsmanager.backend.domain.films.Film;
import com.janflpk.collectionsmanager.backend.domain.films.FilmsCollection;
import com.janflpk.collectionsmanager.backend.repository.FilmRepository;
import com.janflpk.collectionsmanager.backend.service.FilmDbService;
import com.janflpk.collectionsmanager.backend.service.FilmsCollectionDbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmDbServiceTest {

    @Autowired
    private FilmDbService filmDbService;

    @MockBean
    private FilmRepository filmRepository;

    @MockBean
    private FilmsCollectionDbService filmsCollectionDbService;

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
