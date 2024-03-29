package com.janflpk.collectionsmanager.backend.service;

import com.janflpk.collectionsmanager.backend.domain.books.Book;
import com.janflpk.collectionsmanager.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookDbService {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private BooksCollectionDbService booksCollectionDbService;

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public List<Book> findAll(String searchText) {
        return bookRepo.findAll(searchText);
    }

    public Book saveBook (Book book, Long booksCollectionId) {
        book.setBooksCollection(booksCollectionDbService.findById(booksCollectionId));
        return bookRepo.save(book);
    }

    public void deleteBook(final Long id) {
        bookRepo.deleteById(id);
    }

    public Optional<Long> countBooksByBooksCollection_BooksCollectionId(final Long id) {
        return Optional.ofNullable(bookRepo.countBooksByBooksCollection_BooksCollectionId(id)).orElse(Optional.ofNullable(0L));
    }

    public List<Book> findByBooksCollectionId(Long booksCollectionId, String searchText) {
        return bookRepo.findByBooksCollectionId(booksCollectionId, searchText);
    }

/*    public Book findById(final Long id) {
        return bookRepo.findById(id).orElse(new Book());
    }*/

/*    public List<Book> findByBooksCollectionId(Long booksCollectionId) {
        List<Book> list = Optional.ofNullable(bookRepo.findAll()).orElse(new ArrayList<>());
        List<Book> listFiltered = list.stream()
                .filter(book -> (book.getBooksCollection().getBooksCollectionId()).equals(booksCollectionId))
                .collect(Collectors.toList());
        return listFiltered;
    }*/

}
