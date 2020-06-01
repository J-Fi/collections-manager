package com.janflpk.collectionsmanager.repository;

import com.janflpk.collectionsmanager.backend.domain.books.Book;
import com.janflpk.collectionsmanager.backend.domain.books.BooksCollection;
import com.janflpk.collectionsmanager.backend.repository.BookRepository;
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
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRepositoryTest.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BooksCollectionRepository booksCollectionRepository;

    @Test
    public void shouldSaveAndDeleteBook() {
        //Given
        BooksCollection booksCollection = new BooksCollection("MyBooksTEST");
        Book book1 = new Book("1234567890", "1234567890123","title1",
                "publisher1", "synopsys1", "image1",
                "authors1", "subjects1", 2001);

        //When
        booksCollection.getBooks().add(book1);

        book1.setBooksCollection(booksCollection);

        booksCollectionRepository.save(booksCollection);

        bookRepository.save(book1);
        Long book1ID = book1.getBookId();
        bookRepository.deleteById(book1ID);
        LOGGER.info("book1ID = " + book1ID + ", bookRepository.findById(book1ID) " + bookRepository.findById(book1ID));
        Optional<Book> deletedBook = bookRepository.findById(book1ID);

        //Then
        Assert.assertNotEquals(Long.valueOf(0L), book1ID);
        Assert.assertNotNull(book1ID);
        Assert.assertEquals(Optional.empty(), deletedBook);
        //Clean-up
        LOGGER.info("booksCollection.getBooksCollectionId() = " + booksCollection.getBooksCollectionId());
        booksCollectionRepository.deleteById(booksCollection.getBooksCollectionId());
    }

    @Test
    public void shouldFetchBooksByBooksCollectionIdTest() {
        //Given
        BooksCollection bc1 = new BooksCollection("MyBooks1TEST");

        Book book1 = new Book("1234567890", "1234567890123","title1",
                "publisher1", "synopsys1", "image1",
                "authors1", "subjects1", 2001);
        Book book2 = new Book( "1111111111", "2222222222222","title2",
                "publisher2", "synopsys2", "image2",
                "authors2", "subjects2", 2002);

        //When
        bc1.getBooks().add(book1);
        bc1.getBooks().add(book2);

        book1.setBooksCollection(bc1);
        book2.setBooksCollection(bc1);

        booksCollectionRepository.save(bc1);

        //When
        List<Book> booksListReturned = bookRepository.findByBooksCollectionId(bc1.getBooksCollectionId(), "titl");

        //Then
        Assert.assertNotNull(booksListReturned);
        Assert.assertEquals(2, booksListReturned.size());

        //Clean up
        booksCollectionRepository.deleteById(bc1.getBooksCollectionId());
        bookRepository.delete(book1);
        bookRepository.delete(book2);
    }
}