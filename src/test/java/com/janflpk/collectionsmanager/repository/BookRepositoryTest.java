package com.janflpk.collectionsmanager.repository;

import com.janflpk.collectionsmanager.backend.domain.books.Book;
import com.janflpk.collectionsmanager.backend.domain.books.BooksCollection;
import com.janflpk.collectionsmanager.backend.repository.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldSaveAndDeleteBook() {
        //Given
        BooksCollection booksCollection = new BooksCollection(3L, "MyBooks");
        Book book1 = new Book("1234567890", "1234567890123","title1",
                "publisher1", "synopsys1", "image1",
                "authors1", "subjects1", 2001, booksCollection);

        //When
        bookRepository.save(book1);
        Long book1ID = book1.getBookId();
        bookRepository.deleteById(book1ID);

        Optional<Book> deletedBook = bookRepository.findById(book1ID);

        //Then
        Assert.assertNotEquals(Long.valueOf(0L), book1ID);
        Assert.assertNotNull(book1ID);
        Assert.assertEquals(Optional.empty(), deletedBook);
        //Clean-up
        //no need to delete book1 from the database as the test covers saving and deleting
    }

}