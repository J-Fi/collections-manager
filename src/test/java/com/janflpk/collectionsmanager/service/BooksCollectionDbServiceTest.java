package com.janflpk.collectionsmanager.service;

import com.janflpk.collectionsmanager.backend.domain.books.Book;
import com.janflpk.collectionsmanager.backend.domain.books.BooksCollection;
import com.janflpk.collectionsmanager.backend.domain.user.User;
import com.janflpk.collectionsmanager.backend.repository.BooksCollectionRepository;
import com.janflpk.collectionsmanager.backend.service.BooksCollectionDbService;
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
public class BooksCollectionDbServiceTest {

    @Autowired
    private BooksCollectionDbService booksCollectionDbService;

    @MockBean
    private BooksCollectionRepository booksCollectionRepository;

    @Test
    public void shouldSaveBooksCollectionTest() {
        //Given
        User user = new User(1L, "Name", "Surname", "birthday",
                "email", "login", "password");
        List<Book> books = new ArrayList();
        books.add(new Book());
        BooksCollection bc = new BooksCollection("MyBooksCollection");
        BooksCollection bcPersisted = new BooksCollection(Long.valueOf(1L), "MyBooksCollection", user, books);

        when(booksCollectionRepository.save(bc)).thenReturn(bcPersisted);

        //When
        BooksCollection booksCollectionReturned = booksCollectionDbService.saveBooksCollection(bc, user.getUserId());

        //Then
        Assert.assertNotNull(booksCollectionReturned);
        Assert.assertEquals(Long.valueOf(1L), booksCollectionReturned.getBooksCollectionId());
        Assert.assertEquals("MyBooksCollection", booksCollectionReturned.getCollectionName());
    }

    @Test
    public void shouldDeleteBooksCollectionTest() {
        //Given
        User user = new User(1L, "Name", "Surname", "birthday", "email", "login", "password");
        List<Book> books = new ArrayList();
        books.add(new Book());
        BooksCollection bcPersisted = new BooksCollection(Long.valueOf(1L), "MyBooksCollection", user, books);

        //When
        booksCollectionDbService.deleteBooksCollection(1L);

        //Then
        verify(booksCollectionRepository, times(1)).deleteById(eq(bcPersisted.getBooksCollectionId()));
    }

    @Test
    public void shouldFetchBooksCollectionByIdTest() {
        //Given
        User user = new User(1L, "Name", "Surname", "birthday", "email", "login", "password");
        List<Book> books = new ArrayList();
        books.add(new Book());
        BooksCollection bcPersisted = new BooksCollection(Long.valueOf(1L), "MyBooksCollection", user, books);

        when(booksCollectionRepository.findById(1L)).thenReturn(Optional.of(bcPersisted));

        //When
        BooksCollection bcReturned = booksCollectionDbService.findById(bcPersisted.getBooksCollectionId());

        //Then
        Assert.assertNotNull(bcReturned);
        Assert.assertEquals(Long.valueOf(1L), bcReturned.getBooksCollectionId());
    }

    @Test
    public void shouldFetchAllBooksCollectionTest() {
        //Given
        List<BooksCollection> booksCollectionList = new ArrayList<>();

        BooksCollection bc1 = new BooksCollection();
        BooksCollection bc2 = new BooksCollection();

        booksCollectionList.add(bc1);
        booksCollectionList.add(bc2);

        when(booksCollectionRepository.findAll()).thenReturn(booksCollectionList);

        //When
        List<BooksCollection> listReturned = booksCollectionDbService.findAll();

        //Then
        Assert.assertEquals(2, listReturned.size());
    }
}
