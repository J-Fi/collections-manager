package com.janflpk.collectionsmanager.service;

import com.janflpk.collectionsmanager.backend.domain.films.Film;
import com.janflpk.collectionsmanager.backend.domain.films.FilmsCollection;
import com.janflpk.collectionsmanager.backend.domain.user.User;
import com.janflpk.collectionsmanager.backend.repository.FilmsCollectionRepository;
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
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmsCollectionDbServiceTest {

    @Autowired
    private FilmsCollectionDbService filmsCollectionDbService;

    @MockBean
    private FilmsCollectionRepository filmsCollectionRepository;

    @Test
    public void shouldSaveFilmsCollectionTest() {
        //Given
        User user = new User(1L, "Name", "Surname", "birthday",
                "email", "login", "password");
        List<Film> films = new ArrayList<>();
        FilmsCollection fc = new FilmsCollection("MyFilmsCollection");
        FilmsCollection fcPersisted = new FilmsCollection(1L, "MyFilms",user, films);

        when(filmsCollectionRepository.save(fc)).thenReturn(fcPersisted);

        //When
        FilmsCollection fcReturned = filmsCollectionDbService.saveFilmsCollection(fc);

        //Then
        Assert.assertNotNull(fcReturned);
        Assert.assertEquals(Long.valueOf(1L), fcReturned.getFilmsCollectionId());
        Assert.assertEquals("MyFilms", fcReturned.getCollectionName());
    }

    @Test
    public void shouldDeleteFilmsCollectionTest() {
        //Given
        User user = new User(1L, "Name", "Surname", "birthday",
                "email", "login", "password");
        List<Film> films = new ArrayList<>();
        //FilmsCollection fc = new FilmsCollection("MyFilmsCollection");
        FilmsCollection fcPersisted = new FilmsCollection(1L, "MyFilms",user, films);

        //When
        filmsCollectionDbService.deleteFilmsCollection(fcPersisted.getFilmsCollectionId());

        //Then
        verify(filmsCollectionRepository, times(1)).deleteById(eq(fcPersisted.getFilmsCollectionId()));
    }

    @Test
    public void shouldFetchFilmsCollectionByIdTest() {
        //Given
        User user = new User(1L, "Name", "Surname", "birthday",
                "email", "login", "password");
        List<Film> films = new ArrayList<>();
        FilmsCollection fcPersisted = new FilmsCollection(1L, "MyFilms",user, films);

        when(filmsCollectionRepository.findById(fcPersisted.getFilmsCollectionId())).thenReturn(Optional.of(fcPersisted));

        //When
        FilmsCollection fcReturned = filmsCollectionDbService.findById(fcPersisted.getFilmsCollectionId());

        //Then
        Assert.assertNotNull(fcReturned);
        Assert.assertEquals(Long.valueOf(1), fcReturned.getFilmsCollectionId());
    }
}
