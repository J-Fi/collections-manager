package com.janflpk.collectionsmanager.backend.isbndb.facade;

import com.janflpk.collectionsmanager.backend.service.IsbndbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IsbndbFacade {

    @Autowired
    private IsbndbService isbndbService;

    @Autowired
    private BookMapper bookMapper;

    public BookToFrontendFromIsbndbDto getJsonBookDto(String isbn) {
        return bookMapper.mapToBookToFrontendFromIsbndbDto(isbndbService.getJsonBookDto(isbn));
    }
}
