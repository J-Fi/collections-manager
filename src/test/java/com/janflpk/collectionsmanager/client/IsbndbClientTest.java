package com.janflpk.collectionsmanager.client;

import com.janflpk.collectionsmanager.backend.domain.books.Author;
import com.janflpk.collectionsmanager.backend.domain.books.BookDto;
import com.janflpk.collectionsmanager.backend.domain.books.JsonBookDto;
import com.janflpk.collectionsmanager.backend.domain.books.Subject;
import com.janflpk.collectionsmanager.backend.isbndb.client.IsbndbClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IsbndbClientTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsbndbClientTest.class);

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private IsbndbClient isbndbClient = new IsbndbClient();

    @Test
    public void shouldFetchJsonBookDtoTest() {
        //Given
        List<Author> authors = new ArrayList<>();
        Author author = new Author("Author1");
        authors.add(author);

        List<Subject> subjects = new ArrayList<>();
        Subject subject = new Subject("Subject1");
        subjects.add(subject);

        BookDto bookDto = new BookDto("isbn1", "isbn13", "title", "publisher", "synopsys",
                "image", authors, subjects,2000);

        JsonBookDto jsonBookDto = new JsonBookDto(bookDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Host", "api2.isbndb.com");
        headers.add("User-Agent","insomnia/5.12.4");
        headers.add("Authorization", "43705_7acf1129c6d7b89b9e4c467fc3d74355");
        headers.add("Accept", "*/*");
        HttpEntity request = new HttpEntity<>(headers);

        when(restTemplate.exchange("https://api2.isbndb.com/book/isbn1", HttpMethod.GET, request, JsonBookDto.class))
                .thenReturn(new ResponseEntity<JsonBookDto>(jsonBookDto, HttpStatus.OK));

        //When
        JsonBookDto jsonBookDtoReturned = isbndbClient.getJsonBookDto("isbn1");
        LOGGER.info("jsonBookDtoReturned " + jsonBookDtoReturned);

        //Then
        Assert.assertEquals(jsonBookDto, jsonBookDtoReturned);
    }
}
