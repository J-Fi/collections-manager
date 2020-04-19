package com.janflpk.collectionsmanager.backend.service;

import com.janflpk.collectionsmanager.backend.domain.BookDto;
import com.janflpk.collectionsmanager.backend.isbndb.client.IsbndbClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IsbndbService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IsbndbService.class);

    @Autowired
    private IsbndbClient isbndbClient;

    public BookDto getJsonBookDto(String isbn) {
        BookDto bookDto = isbndbClient.getJsonBookDto(isbn).getBody().getBookDto();
        LOGGER.info("See the content of a bookDto object: " + bookDto.toString());
        return Optional.ofNullable(bookDto).orElse(new BookDto());
    }
}