package com.janflpk.collectionsmanager.repository;

import com.janflpk.collectionsmanager.backend.domain.films.Film;
import com.janflpk.collectionsmanager.backend.domain.films.FilmsCollection;
import com.janflpk.collectionsmanager.backend.repository.FilmRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmRepositoryTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(FilmRepositoryTest.class);

    @Autowired
    private FilmRepository filmRepository;

    @Test
    public void shouldSaveAndDeleteFilm() {
        //Given
        FilmsCollection fc = new FilmsCollection(37L, "MyFilms");
        Film film1 = new Film("FilmTitle1", "2000", "163", "DirectorName1",
                "Writers1", "Actors1", "Plot1", "language1", "country1",
                "posterLink1", "production1", fc);

        //When
        filmRepository.save(film1);
        Long film1ID = film1.getFilmId();
        LOGGER.info("test film1 id = " + film1ID);
        filmRepository.deleteById(film1ID);

        Optional<Film> deletedFilm = filmRepository.findById(film1ID);

        //Then
        Assert.assertNotEquals(Long.valueOf(0L), film1ID);
        Assert.assertNotNull(film1ID);
        Assert.assertEquals(Optional.empty(), deletedFilm);

        //Clean-up
        //no need to delete book1 from the database as the test covers saving and deleting
    }
}
