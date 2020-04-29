package com.janflpk.collectionsmanager.backend.omdb.client;

import com.janflpk.collectionsmanager.backend.domain.films.FilmDto;
import com.janflpk.collectionsmanager.backend.omdb.config.OmdbConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Component
public class OmdbClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(OmdbClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OmdbConfig omdbConfig;

    public FilmDto getFilm (String filmTitle) {
        try {
            return Optional.ofNullable(restTemplate.getForObject(getUrl(filmTitle), FilmDto.class)).orElse(new FilmDto());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new FilmDto();
        }
    }

    private URI getUrl(String filmTitle) {
        return UriComponentsBuilder.fromHttpUrl(omdbConfig.getOmdbApiEndpoint())
                .queryParam("apikey", "14ef4a96")
                .queryParam("t", filmTitle)
                .build().encode().toUri();
    }
}
