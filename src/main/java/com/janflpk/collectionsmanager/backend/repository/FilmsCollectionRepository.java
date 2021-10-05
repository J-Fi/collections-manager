package com.janflpk.collectionsmanager.backend.repository;

import com.janflpk.collectionsmanager.backend.domain.books.BooksCollection;
import com.janflpk.collectionsmanager.backend.domain.films.FilmsCollection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmsCollectionRepository extends CrudRepository<FilmsCollection, Long> {

    @Override
    List<FilmsCollection> findAll();

    List<FilmsCollection> getFilmsCollectionsByUserId(@Param("userId") Long userId);
}
