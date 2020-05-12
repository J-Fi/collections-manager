package com.janflpk.collectionsmanager.backend.isbndb.client;

import com.janflpk.collectionsmanager.backend.domain.books.JsonBookDto;
import com.janflpk.collectionsmanager.backend.isbndb.config.IsbndbConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class IsbndbClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(IsbndbClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IsbndbConfig isbndbConfig;

    public JsonBookDto getJsonBookDto(String isbn) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Host", "api2.isbndb.com");
            headers.add("User-Agent","insomnia/5.12.4");
            headers.add("Authorization", "43705_7acf1129c6d7b89b9e4c467fc3d74355");
            headers.add("Accept", "*/*");
            HttpEntity request = new HttpEntity<>(headers);
            LOGGER.info("IsbndbClient request " + request);
            ResponseEntity<JsonBookDto> bookResponse = restTemplate.exchange(isbndbConfig.getIsbndbApiEndpoint() + "/" + isbn, HttpMethod.GET, request, JsonBookDto.class);
            return Optional.ofNullable(bookResponse.getBody()).orElse(new JsonBookDto());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new JsonBookDto();
        }

    }
//
/*    private URI getUrl(String isbn) {
        return UriComponentsBuilder.fromHttpUrl(isbndbConfig.getIsbndbApiEndpoint())
                .queryParam("q", isbn)
                .queryParam("fields", "items/volumeInfo/title,items/volumeInfo/subtitle,items/volumeInfo/authors").build().encode().toUri();
                //.queryParam("format", "json").build().encode().toUri();
    }*/

}

