package com.janflpk.collectionsmanager.backend.isbndb.facade;

import com.janflpk.collectionsmanager.backend.domain.books.Book;
import com.janflpk.collectionsmanager.backend.mapper.BookMapper;
import com.janflpk.collectionsmanager.backend.service.IsbndbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IsbndbFacade {

    @Autowired
    private IsbndbService isbndbService;

    @Autowired
    private BookMapper bookMapper;

    public Book getBook(String isbn) {
        return bookMapper.mapToBook(isbndbService.getBookDto(isbn));
    }
}
