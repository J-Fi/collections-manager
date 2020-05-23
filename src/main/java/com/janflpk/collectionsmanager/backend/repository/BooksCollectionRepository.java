package com.janflpk.collectionsmanager.backend.repository;

import com.janflpk.collectionsmanager.backend.domain.books.BooksCollection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksCollectionRepository extends CrudRepository<BooksCollection, Long> {

    @Override
    List<BooksCollection> findAll();
}
