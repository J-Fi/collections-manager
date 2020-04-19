package com.janflpk.collectionsmanager.backend.isbndb.client;

import com.janflpk.collectionsmanager.backend.isbndb.config.IsbndbConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class IsbndbClient {
    @Component
    public class IsbndbClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(IsbndbClient.class);

        @Autowired
        private RestTemplate restTemplate;

        @Autowired
        private IsbndbConfig isbndbConfig;

        public ResponseEntity<JsonBookDto> getJsonBookDto(String isbn) {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Host", "api2.isbndb.com");
                headers.add("User-Agent","insomnia/5.12.4");
                headers.add("Authorization", "43705_7acf1129c6d7b89b9e4c467fc3d74355");
                headers.add("Accept", "*/*");
                HttpEntity request = new HttpEntity<>(headers);
                ResponseEntity<JsonBookDto> bookResponse = restTemplate.exchange(isbndbConfig.getIsbndbApiEndpoint() + "/" + isbn, HttpMethod.GET, request, JsonBookDto.class);
                return Optional.ofNullable(bookResponse).orElse(new ResponseEntity<JsonBookDto>(HttpStatus.NOT_FOUND));
            } catch (RestClientException e) {
                LOGGER.error(e.getMessage(), e);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
}
