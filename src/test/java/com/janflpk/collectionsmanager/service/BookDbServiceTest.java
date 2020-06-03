package com.janflpk.collectionsmanager.service;

import com.janflpk.collectionsmanager.backend.domain.books.Book;
import com.janflpk.collectionsmanager.backend.domain.books.BooksCollection;
import com.janflpk.collectionsmanager.backend.repository.BookRepository;
import com.janflpk.collectionsmanager.backend.service.BookDbService;
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
public class BookDbServiceTest {

    @Autowired
    private BookDbService bookDbService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BooksCollectionDbService booksCollectionDbService;

    @Test
    public void shouldFetchAllBooksContainingSpecificStringTest() {
        //Given
        Book book1 = new Book("1234567890", "1234567890123","title1",
                "publisher1", "synopsys1", "image1",
                "authors1", "subjects1", 2001);
        Book book2 = new Book("1111111111", "2222222222222","title2",
                "publisher2", "synopsys2", "image2",
                "authors2", "subjects2", 2002);
        Book bk = new Book("3333333333", "4444444444444","title3",
                "publisher3", "synopsys3", "image3",
                "authors3", "subjects3", 2003);

        List<Book> list = new ArrayList<>();
        list.add(book1);
        list.add(book2);

        when(bookRepository.findAll("book")).thenReturn(list);

        //When
        List<Book> returnedList = bookDbService.findAll("book");

        //Then
        Assert.assertEquals(2, returnedList.size());
    }

    @Test
    public void shouldSaveBookTest() {
        //Given
        Book book1 = new Book("1234567890", "1234567890123","title1",
                "publisher1", "synopsys1", "image1",
                "authors1", "subjects1", 2001);

        BooksCollection booksCollection = new BooksCollection(1L, "MyBooks");

        when(bookRepository.save(book1)).thenReturn(book1);
        when(booksCollectionDbService.findById(1L)).thenReturn(booksCollection);
        //When
        Book bookReturned = bookDbService.saveBook(book1, booksCollection.getBooksCollectionId());

        //Then
        Assert.assertEquals("1234567890", bookReturned.getIsbn());
        Assert.assertEquals(Long.valueOf(1L), bookReturned.getBooksCollection().getBooksCollectionId());
    }

    @Test
    public void shouldDeleteBook() {
        //Given
        BooksCollection bc = new BooksCollection(2L, "MyBooks");
        Book book1 = new Book("1234567890", "1234567890123","title1",
                "publisher1", "synopsys1", "image1",
                "authors1", "subjects1", 2001, bc);
        book1.setBookId(1L);

        //When
        bookDbService.deleteBook(book1.getBookId());

        //Then
        verify(bookRepository, times(1)).deleteById(eq(book1.getBookId()));
    }

    @Test
    public void shouldFetchBooksByBooksCollectionIdTest() {
        //Given
        BooksCollection bc1 = new BooksCollection(1L, "MyBooks1");

        Book book1 = new Book(1L, "1234567890", "1234567890123","title1",
                "publisher1", "synopsys1", "image1",
                "authors1", "subjects1", 2001, bc1);
        Book book2 = new Book(2L, "1111111111", "2222222222222","title2",
                "publisher2", "synopsys2", "image2",
                "authors2", "subjects2", 2002, bc1);

        List<Book> booksList = new ArrayList<>();
        booksList.add(book1);
        booksList.add(book2);

        when(bookRepository.findByBooksCollectionId(1L, "text")).thenReturn(booksList);

        //When
        List<Book> booksListReturned = bookDbService.findByBooksCollectionId(1L, "text");

        //Then
        Assert.assertEquals(2, booksListReturned.size());
    }

    @Test
    public void shouldFetchAllBooksTest() {
        //Given
        BooksCollection bc1 = new BooksCollection(1L, "MyBooks1");
        Book book1 = new Book(1L, "1234567890", "1234567890123","title1",
                "publisher1", "synopsys1", "image1",
                "authors1", "subjects1", 2001, bc1);
        Book book2 = new Book(2L, "1111111111", "2222222222222","title2",
                "publisher2", "synopsys2", "image2",
                "authors2", "subjects2", 2002, bc1);

        List<Book> booksList = new ArrayList<>();
        booksList.add(book1);
        booksList.add(book2);

        when(bookRepository.findAll()).thenReturn(booksList);

        //When
        List<Book> bookListReturned = bookDbService.findAll();

        //Then
        Assert.assertNotNull(bookListReturned);
        Assert.assertEquals(2, bookListReturned.size());
    }

    @Test
    public void shouldFetchNumberOfBooksInCollectionTest() {
        //Given
        BooksCollection bc1 = new BooksCollection(1L, "MyBooks1");
        Book book1 = new Book(1L, "1234567890", "1234567890123","title1",
                "publisher1", "synopsys1", "image1",
                "authors1", "subjects1", 2001, bc1);
        Book book2 = new Book(2L, "1111111111", "2222222222222","title2",
                "publisher2", "synopsys2", "image2",
                "authors2", "subjects2", 2002, bc1);

        List<Book> booksList = new ArrayList<>();
        booksList.add(book1);
        booksList.add(book2);

        when(bookRepository.countBooksByBooksCollection_BooksCollectionId(1L)).thenReturn(Optional.of(2L));

        //When
        Optional<Long> numberOfItemsReturned = bookDbService.countBooksByBooksCollection_BooksCollectionId(1L);

        //Then
        Assert.assertNotNull(numberOfItemsReturned);
        Assert.assertEquals(Long.valueOf(2L), numberOfItemsReturned.orElse(0L));
    }
}
