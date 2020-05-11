package com.janflpk.collectionsmanager.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.janflpk.collectionsmanager.backend.domain.films.FilmDto;
import com.janflpk.collectionsmanager.backend.omdb.client.OmdbClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OmdbClientTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OmdbClientTest.class);

    @Autowired
    private OmdbClient omdbClient;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void shouldFetchFilmDto() throws URISyntaxException, JsonProcessingException {
        //Given
        FilmDto filmDto = new FilmDto("FilmTitle1", "2000", "163", "DirectorName1",
                "Writers1", "Actors1", "Plot1", "language1", "country1",
                "posterLink1", "production1");

        mockServer.expect(
                        ExpectedCount.once(),
                        requestTo(new URI("http://www.omdbapi.com?apikey=14ef4a96&t=FilmTitle1")))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(filmDto))
                    );

        //When
        FilmDto filmDtoReturned = omdbClient.getFilm("FilmTitle1");
        LOGGER.info("tytuł filmu " + filmDtoReturned.getFilmTitle());

        //Then
        mockServer.verify();
        Assert.assertEquals(filmDto, filmDtoReturned);
        Assert.assertEquals("FilmTitle1", filmDtoReturned.getFilmTitle());
    }
}
