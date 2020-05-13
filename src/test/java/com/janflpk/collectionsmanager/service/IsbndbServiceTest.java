package com.janflpk.collectionsmanager.service;

import com.janflpk.collectionsmanager.backend.domain.books.Author;
import com.janflpk.collectionsmanager.backend.domain.books.BookDto;
import com.janflpk.collectionsmanager.backend.domain.books.JsonBookDto;
import com.janflpk.collectionsmanager.backend.domain.books.Subject;
import com.janflpk.collectionsmanager.backend.isbndb.client.IsbndbClient;
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
public class IsbndbServiceTest {

    @Autowired
    private IsbndbService isbndbService;

    @MockBean
    private IsbndbClient isbndbClient;

    @Test
    public void shouldFetchBookDtoTest() {
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

        JsonBookDto jsonBookDto = new JsonBookDto(bookDto1);

        when(isbndbClient.getJsonBookDto("isbn1")).thenReturn(jsonBookDto);

        //When
        BookDto bookDtoReturned = isbndbService.getBookDto("isbn1");

        //Then
        Assert.assertNotNull(bookDtoReturned);
        Assert.assertEquals(bookDto1, bookDtoReturned);
    }
}
