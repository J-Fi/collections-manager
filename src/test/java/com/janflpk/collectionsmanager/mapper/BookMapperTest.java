package com.janflpk.collectionsmanager.mapper;

import com.janflpk.collectionsmanager.backend.domain.books.Author;
import com.janflpk.collectionsmanager.backend.domain.books.Book;
import com.janflpk.collectionsmanager.backend.domain.books.BookDto;
import com.janflpk.collectionsmanager.backend.domain.books.Subject;
import com.janflpk.collectionsmanager.backend.mapper.BookMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMapperTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookMapperTest.class);

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void mapAuthorsListToStringWhenListIsNullOrEmpty() {
        //Given
        List<Author> authorsNull = null;
        List<Author> authorsEmpty = new ArrayList<>();

        //When
        LOGGER.info("authorsNull' list in mapAuthorsListToStringWhenListIsNull() should be null and is = " + authorsNull);
        LOGGER.info("authorsEmpty' list in mapAuthorsListToStringWhenListIsNull() should be empty and is = " + authorsEmpty.isEmpty());
        String authorsNullReturned = bookMapper.mapAuthorsListToString(authorsNull);
        String authorsEmptyReturned = bookMapper.mapAuthorsListToString(authorsEmpty);

        //Then
        Assert.assertEquals("", authorsNullReturned);
        Assert.assertEquals("", authorsEmptyReturned);
    }

    @Test
    public void mapAuthorsListToStringTest() {
        //Given
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author("Author1");
        Author author2 = new Author("Author2");
        authors.add(author1);
        authors.add(author2);

        //When
        String authorsReturned = bookMapper.mapAuthorsListToString(authors);

        //Then
        Assert.assertEquals("Author1; Author2", authorsReturned);
    }

    @Test
    public void mapToBookTest() {
        //Given
        List<Author> authors = new ArrayList<>();
        Author author1 = new Author("Author1");
        Author author2 = new Author("Author2");
        authors.add(author1);
        authors.add(author2);

        List<Subject> subjects = new ArrayList<>();
        Subject subject1 = new Subject("Subject1");
        Subject subject2 = new Subject("Subject2");
        subjects.add(subject1);
        subjects.add(subject2);

        BookDto bookDto1 = new BookDto("isbn1", "isbn13", "title", "publisher", "synopsys",
                "image", authors, subjects,2000);

        //When
        Book book = bookMapper.mapToBook(bookDto1);

        //Then
        Assert.assertNotNull(book);
        Assert.assertEquals(Book.class, book.getClass());
        Assert.assertEquals("isbn1", book.getIsbn());
        Assert.assertEquals("Author1; Author2", book.getAuthors());
        Assert.assertEquals("Subject1; Subject2", book.getSubjects());

    }

    @Test
    public void mapToBookWhenBookDtoIsNullOrEmptyTest() {
        //Given
        BookDto bookDtoNull = null;
        BookDto bookDtoEmpty = new BookDto();

        //When
        Book bookNull = bookMapper.mapToBook(bookDtoNull);
        Book bookEmpty = bookMapper.mapToBook(bookDtoEmpty);
        LOGGER.info("bookNull = " + bookNull);
        LOGGER.info("new Book() " + (new Book()));
        LOGGER.info("bookEmpty = " + bookEmpty);

        //Then
        Assert.assertNotNull(bookNull);
        Assert.assertNull(bookNull.getIsbn());
        Assert.assertNull(bookNull.getIsbn13());
        Assert.assertNull(bookNull.getAuthors());
        Assert.assertNull(bookNull.getTitle());
        Assert.assertNull(bookNull.getImage());
        Assert.assertNull(bookNull.getSynopsys());
        Assert.assertNull(bookNull.getPublisher());
        Assert.assertNull(bookNull.getPublishDate());
        Assert.assertNull(bookNull.getSubjects());

        Assert.assertNotNull(bookEmpty);
        Assert.assertNull(bookEmpty.getIsbn());
        Assert.assertNull(bookEmpty.getIsbn13());
        Assert.assertTrue(bookEmpty.getAuthors().isEmpty());
        Assert.assertNull(bookEmpty.getTitle());
        Assert.assertNull(bookEmpty.getImage());
        Assert.assertNull(bookEmpty.getSynopsys());
        Assert.assertNull(bookEmpty.getPublisher());
        Assert.assertNull(bookEmpty.getPublishDate());
        Assert.assertTrue(bookEmpty.getSubjects().isEmpty());
    }

    @Test
    public void mapStringToAuthorsListTest() {
        //Given
        String authors = "Author1; Author2";

        //When
        List<Author> list = bookMapper.mapStringToAuthorsList(authors);

        //Then
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void mapStringToSubjectsListTest() {
        //Given
        String subjects = "Subject1; Subject2";

        //When
        List<Subject> list = bookMapper.mapStringToSubjectsList(subjects);

        //Then
        Assert.assertEquals(2, list.size());
    }

    @Test
    public void mapStringToSubjectsListWhenStringIsNullTest() {
        //Given
        String subjects = null;

        //When
        List<Subject> list = bookMapper.mapStringToSubjectsList(subjects);

        //Then
        Assert.assertTrue(list.isEmpty());
    }
}
