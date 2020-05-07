package com.janflpk.collectionsmanager.facade;

import com.janflpk.collectionsmanager.backend.domain.books.Author;
import com.janflpk.collectionsmanager.backend.domain.books.Book;
import com.janflpk.collectionsmanager.backend.domain.books.BookDto;
import com.janflpk.collectionsmanager.backend.domain.books.Subject;
import com.janflpk.collectionsmanager.backend.isbndb.facade.IsbndbFacade;
import com.janflpk.collectionsmanager.backend.service.IsbndbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IsbndbFacadeTest {

    @Autowired
    private IsbndbFacade isbndbFacade;

    @MockBean
    private IsbndbService isbndbService;

    @Test
    public void shouldFetchBookTest() {
        //Given
        Author author = new Author("A1");
        Subject subject = new Subject("Polska");

        List<Author> listAuthors = new ArrayList<>();
        List<Subject> listSubjects = new ArrayList<>();

        listAuthors.add(author);
        listSubjects.add(subject);

        BookDto bookDto = new BookDto("1234", "12345",
                "Title1", "Publisher1",
                "Synopsys1", "url to image",
                listAuthors, listSubjects,
                2021);

        when(isbndbService.getJsonBookDto("1234")).thenReturn(bookDto);

        //When
        Book book = isbndbFacade.getBook("1234");

        //Then
        Assert.assertNotNull(book);
        Assert.assertEquals("1234", book.getIsbn());
        Assert.assertEquals("12345", book.getIsbn13());
        Assert.assertEquals("A1", book.getAuthors());
        Assert.assertEquals("url to image", book.getImage());
        Assert.assertEquals(Integer.valueOf(2021), book.getPublishDate());
        Assert.assertEquals("Publisher1", book.getPublisher());
        Assert.assertEquals("Polska", book.getSubjects());
        Assert.assertEquals("Synopsys1", book.getSynopsys());
        Assert.assertEquals("Title1", book.getTitle());
    }
}
