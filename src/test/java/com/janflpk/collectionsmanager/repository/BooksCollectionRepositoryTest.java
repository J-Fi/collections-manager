package com.janflpk.collectionsmanager.repository;

import com.janflpk.collectionsmanager.backend.domain.books.BooksCollection;
import com.janflpk.collectionsmanager.backend.repository.BooksCollectionRepository;
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
public class BooksCollectionRepositoryTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(BooksCollectionRepositoryTest.class);

    @Autowired
    private BooksCollectionRepository booksCollectionRepository;

    @Test
    public void shouldFetchAllBooksCollectionTest() {
        //Given
        BooksCollection bc1 = new BooksCollection();

        //When
        booksCollectionRepository.save(bc1);
        List<BooksCollection> allBooksCollections = booksCollectionRepository.findAll();

        //Then
        LOGGER.info("Liczba kolekcji książek = " + allBooksCollections.size());
        Assert.assertNotNull(allBooksCollections);
        Assert.assertNotEquals(0, allBooksCollections);

        //Clean up
        booksCollectionRepository.delete(bc1);
    }
}
