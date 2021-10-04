package com.janflpk.collectionsmanager.backend.service;

import com.janflpk.collectionsmanager.backend.domain.books.BooksCollection;
import com.janflpk.collectionsmanager.backend.repository.BooksCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BooksCollectionDbService {

    @Autowired
    private UserDbService userDbService;

    @Autowired
    private BooksCollectionRepository booksCollectionRepo;

    public BooksCollection saveBooksCollection (BooksCollection booksCollection, Long userId) {
        booksCollection.setUser(userDbService.findById(userId));
        return booksCollectionRepo.save(booksCollection);
    }

    public void deleteBooksCollection(final Long id) {
        booksCollectionRepo.deleteById(id);
    }

    public BooksCollection findById(final Long id) {
        return booksCollectionRepo.findById(id).orElse(new BooksCollection());
    }

    public List<BooksCollection> findAll() {
        return booksCollectionRepo.findAll();
    }

    public void updateBooksCollection(BooksCollection booksCollection) {
        booksCollectionRepo.save(booksCollection);
    }

    public List<BooksCollection> findBooksCollectionsByUserId(final Long userId) {
        return Optional.ofNullable(booksCollectionRepo.getBooksCollectionsByUserId(userId)).orElse(new ArrayList<>());
    }

    public Long getNumberOfBooksInCollection(final Long booksCollectionid) {
        return booksCollectionRepo.getNumberOfBooks(booksCollectionid);
    }

}
