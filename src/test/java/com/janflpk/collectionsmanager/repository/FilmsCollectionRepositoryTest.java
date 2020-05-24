package com.janflpk.collectionsmanager.repository;

import com.janflpk.collectionsmanager.backend.domain.films.FilmsCollection;
import com.janflpk.collectionsmanager.backend.repository.FilmsCollectionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FilmsCollectionRepositoryTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(FilmsCollectionRepositoryTest.class);

    @Autowired
    private FilmsCollectionRepository filmsCollectionRepository;

    @Test
    public void shouldFetchAllFilmsCollectionTest() {
        //Given & When
        List<FilmsCollection> allFilmsCollections = filmsCollectionRepository.findAll();

        //Then
        LOGGER.info("Lista wszystkich kolekcji filmów = " + allFilmsCollections.size());
        Assert.assertNotNull(allFilmsCollections);
        Assert.assertNotEquals(0, allFilmsCollections);
    }
}
